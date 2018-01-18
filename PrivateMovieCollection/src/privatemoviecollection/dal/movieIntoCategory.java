/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.PMCException;

/**
 *
 * @author pmj
 */
public class movieIntoCategory {

    ConnectionManager cm = new ConnectionManager();

    public void addCategoryToMovie(int categoryId, int movieId) throws PMCException {
        String sql = "INSERT INTO CatMovie VALUES(" + categoryId + ", " + movieId + ");";
        try (Connection con = cm.getConnection()) {
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(movieIntoCategory.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("Could not connect to database.");
        } catch (SQLException ex) {
            Logger.getLogger(movieIntoCategory.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("Could not add category to movie.");
        }
    }

    public ObservableList<Movie> getCategoriesToMovie(int categoryId) throws PMCException {
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        try (Connection con = cm.getConnection()) {
           String sqlJoin = "SELECT CatMovie.mov_id, Movie.movie_title, Movie.imdb_movie_rating, Movie.private_movie_rating, Movie.filelink, Movie.lastview FROM CatMovie INNER JOIN Movie ON CatMovie.mov_id=Movie.movie_id INNER JOIN Category ON Category.category_id=CatMovie.cat_id WHERE cat_id =" + categoryId; 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlJoin);
            while (rs.next()) {
                Movie currentMovieWithCat = new Movie();
                currentMovieWithCat.setId(rs.getInt("mov_id"));
                currentMovieWithCat.setMovieName(rs.getString("movie_title"));
                currentMovieWithCat.setImdbRating(rs.getDouble("imdb_movie_rating"));
                currentMovieWithCat.setPrivateRating(rs.getDouble("private_movie_rating"));
                currentMovieWithCat.setFileLink(rs.getString("filelink"));
                currentMovieWithCat.setLastView(rs.getLong("lastview"));
                movies.add(currentMovieWithCat);
            }  
        } catch (SQLServerException ex) {
            Logger.getLogger(movieIntoCategory.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("Could not connect to database. Check your connection");
        } catch (SQLException ex) {
            Logger.getLogger(movieIntoCategory.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("Failed to get categories.");  
        }
       return movies;
    }
    
    public void removeCatOnMovie(int id) throws PMCException{
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM CatMovie WHERE Mov_id="+ id);
        }  catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("Error deleting the movie category.");
        }
    }
}
