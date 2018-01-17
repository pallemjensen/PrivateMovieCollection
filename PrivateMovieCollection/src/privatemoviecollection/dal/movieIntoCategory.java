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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.PMCException;

/**
 *
 * @author pmj
 */
public class movieIntoCategory {

    ConnectionManager cm = new ConnectionManager();

    public void addCategoryToMovie(ArrayList<Integer> list) throws PMCException {
        int categoryId = list.get(0);
        int movieId = list.get(1);
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

    public ObservableList<Integer> getCategoriesToMovie(int categoryId) throws PMCException {
        ObservableList<Integer> listMoviesBelongsToCategory = FXCollections.observableArrayList();
        String sqlJoin = "SELECT Movie.imdb_movie_rating, Movie.private_movie_rating, CatMovie.category_id,Category.category_name,Movie.movie_title FROM CatMovie INNER JOIN Movie ON CatMovie.movie_id=Movie.movie_id INNER JOIN Category ON Category.category_id=CatMovie.category_id";
        try (Connection con = cm.getConnection()) {
            PreparedStatement preparedStmt = con.prepareStatement(sqlJoin);
            preparedStmt.executeQuery();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT movie_id FROM CatMovie WHERE category_id =" + categoryId);
            
            while (rs.next()) {
                int movie_id = 0;
                movie_id = listMoviesBelongsToCategory.get(rs.getInt(movie_id));
                listMoviesBelongsToCategory.add(movie_id);  
            }

        } catch (SQLServerException ex) {
            Logger.getLogger(movieIntoCategory.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("Could not connect to database. Check your connection");
        } catch (SQLException ex) {
            Logger.getLogger(movieIntoCategory.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("Failed to get categories.");
             
        }
       return listMoviesBelongsToCategory;
        
    }
}
