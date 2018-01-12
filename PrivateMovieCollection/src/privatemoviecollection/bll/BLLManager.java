/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.List;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.PMCException;
import privatemoviecollection.dal.CategoryDAO;
import privatemoviecollection.dal.MovieDAO;

/**
 *
 * @author pmj
 */
public class BLLManager {

    MovieDAO movieDAO = new MovieDAO();
    CategoryDAO categoryDAO = new CategoryDAO();

    public Movie createMovie(String movieName, double imdbRating, double privateRating, String fileLink, long lastView) throws PMCException {
        Movie newMovie = movieDAO.createMovie(movieName, imdbRating, privateRating, fileLink, lastView);
        return newMovie;
    }

    public List<Movie> getAllMovies() throws PMCException {
        return movieDAO.getAllMovies();
    }

    public Category createCategory(String categoryName) throws SQLException {
        Category newCategory = categoryDAO.createCategory(categoryName);
        return newCategory;
    }

    public List<Category> getAllCategories() throws SQLServerException, SQLException {
        return categoryDAO.getAllCategories();
    }
    public void remove(Movie movie){
        movieDAO.remove(movie);
    }
    
    public void remove(Category category){
        categoryDAO.remove(category);
    }

    public void editPersonalRating(int id, double value) {
        movieDAO.editPersonalRating(id, value);
    }

    public void updateLastView(Movie movie, long newView) {
        movieDAO.updateLastView(movie, newView);
    }
    
    
    
}
