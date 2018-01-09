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
import privatemoviecollection.be.movie;

/**
 *
 * @author pmj
 */
public class MovieDAO {
    ConnectionManager cm = new ConnectionManager();

    public movie createMovie(String movieName, double imdbRating, double privateRating, String fileLink, long lastView) {
     try (Connection con = cm.getConnection())
        {
            String sql = "INSERT INTO Movie VALUES (?, ?, ?, ?, ?);";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, movieName);
            statement.setDouble(2, imdbRating);
            statement.setDouble(3,privateRating);
            statement.setString(4, fileLink);
            statement.setLong(5, lastView);

            if(statement.executeUpdate() == 1);
            {
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                movie newMovie = new movie(id, movieName, imdbRating, privateRating, fileLink, lastView);
                return newMovie;
            }
            
        } catch (SQLServerException ex) {
           Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }  
    
    public List<movie> getAllMovies() throws SQLServerException, SQLException{
        List<movie> movies = new ArrayList<>();
        
        try (Connection con = cm.getConnection())
        {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Movie");          
            
            while (rs.next()) {
                movie currentMovie = new movie();
                currentMovie.setId(rs.getInt("movie_id"));
                currentMovie.setMovieName(rs.getString("movie_title"));
                currentMovie.setImdbRating(rs.getDouble("imdb_movie_rating"));
                currentMovie.setPrivateRating(rs.getDouble("private_movie_rating"));
                currentMovie.setFileLink(rs.getString("filelink"));
                currentMovie.setLastView(rs.getLong("lastview"));
                movies.add(currentMovie);
            }
        }
     return movies;   
    }
}
