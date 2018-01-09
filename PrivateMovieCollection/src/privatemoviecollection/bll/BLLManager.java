/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.List;
import privatemoviecollection.be.category;
import privatemoviecollection.be.movie;
import privatemoviecollection.dal.CategoryDAO;
import privatemoviecollection.dal.MovieDAO;

/**
 *
 * @author pmj
 */
public class BLLManager {

    MovieDAO movieDAO = new MovieDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    public movie createMovie(String movieName, double imdbRating, double privateRating, String fileLink, long lastView) {
        movie newMovie = movieDAO.createMovie(movieName, imdbRating, privateRating, fileLink, lastView);
        return newMovie;
    }

    public List<movie> getAllMovies() throws SQLServerException, SQLException {
        return movieDAO.getAllMovies();
    }

    public category createCategory(String categoryName) throws SQLException {
        return categoryDAO.createCategory(categoryName);
    }

    public List<category> getAllCategories() throws SQLServerException, SQLException {
        return categoryDAO.getAllCategories();
    }
    
    
    
}
