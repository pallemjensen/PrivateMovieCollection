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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.PMCException;

/**
 *Our Category class where we manage our calls to the DB
 *We can create, load, delete and edit movies.
 * 
 */
public class MovieDAO {

    ConnectionManager cm = new ConnectionManager();

    public Movie createMovie(String movieName, double imdbRating, double privateRating, String fileLink, long lastView) throws PMCException  {
        try (Connection con = cm.getConnection()) {
            String sql = "INSERT INTO Movie VALUES (?, ?, ?, ?, ?);";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, movieName);
            statement.setDouble(2, imdbRating);
            statement.setDouble(3, privateRating);
            statement.setString(4, fileLink);
            statement.setLong(5, lastView);

            if (statement.executeUpdate() == 1)
            {
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                Movie newMovie = new Movie(id, movieName, imdbRating, privateRating, fileLink, lastView);
                return newMovie;
            }

        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLServerException. An error occurred. You cannot create a movie");
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLException.An error occurred. You cannot create a movie");
        }
        return null;
    }

    public List<Movie> getAllMovies() throws PMCException {
        List<Movie> movies = new ArrayList<>();

        try (Connection con = cm.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Movie");

            while (rs.next()) {
                Movie currentMovie = new Movie();
                currentMovie.setId(rs.getInt("movie_id"));
                currentMovie.setMovieName(rs.getString("movie_title"));
                currentMovie.setImdbRating(rs.getDouble("imdb_movie_rating"));
                currentMovie.setPrivateRating(rs.getDouble("private_movie_rating"));
                currentMovie.setFileLink(rs.getString("filelink"));
                currentMovie.setLastView(rs.getLong("lastview"));
                movies.add(currentMovie);
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLServerException. Error in connect to DB.");
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLException. Error in getting all movies from DB.");
        }
        return movies;
    }
    
    public void remove(Movie movie) throws PMCException{
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM Movie WHERE Movie_id="+movie.getId());
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("Could not connect to database. Check you connection.");
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLException. Error deleting the movie.");
        }
    }

    public void editPersonalRating(int id, double value) throws PMCException {
        String query = "UPDATE Movie SET private_movie_rating = ? WHERE movie_id = ?;";
        try (Connection con = cm.getConnection()){
            PreparedStatement preparedStmt = con.prepareStatement(query);
         preparedStmt.setDouble(1, value);
         preparedStmt.setInt(2, id);
         preparedStmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLException. Error connecting to the database.");
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLException. Error editing the rating.");
        }
    }

    public void updateLastView(Movie movie, long newView) throws PMCException {
        int id = movie.getId();
        String query = "UPDATE Movie SET lastview = ? WHERE movie_id = ?;";
        try (Connection con = cm.getConnection()){
            PreparedStatement preparedStmt = con.prepareStatement(query);
         preparedStmt.setLong(1, newView);
         preparedStmt.setInt(2, id);
         preparedStmt.executeUpdate();
        } catch (SQLServerException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLException. Could not connect to the database.");
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLException. Could not update last view.");
        }
    }
}
