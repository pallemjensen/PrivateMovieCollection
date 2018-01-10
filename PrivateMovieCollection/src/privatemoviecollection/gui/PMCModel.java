/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.bll.BLLManager;

/**
 *
 * @author pmj
 */
public class PMCModel {

    private final BLLManager bllManager = new BLLManager();

    private final ObservableList<Movie> movies
            = FXCollections.observableArrayList();

    private final ObservableList<Category> categories
            = FXCollections.observableArrayList();

    public void addNewMovie(String movieName, double imdbRating, double privateRating, String fileLink, long lastView) {
        Movie newMovie = bllManager.createMovie(movieName, imdbRating, privateRating, fileLink, lastView);
        movies.add(newMovie);
    }
    
    public Category createCategory(String categoryName) throws SQLException {
        Category newCategory = bllManager.createCategory(categoryName);
        return newCategory;
    }

    public List<Movie> getAllMovies() throws SQLServerException, SQLException {
        return bllManager.getAllMovies(); 
    }
    
     public List<Category> getAllCategories() throws SQLServerException, SQLException {
        return bllManager.getAllCategories();
    }

    public void loadMovies() throws SQLException {
        List<Movie> loadedMovies = bllManager.getAllMovies();
        movies.clear();
        movies.addAll(loadedMovies);
    }
    
     public void loadCategories() throws SQLException {
            List<Category> loadedCategories = bllManager.getAllCategories();
            categories.clear();
            categories.addAll(loadedCategories);
    }

    public ObservableList<Movie> getMovies() {
        return movies;
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }

    public void remove(Movie movie) {
        bllManager.remove(movie);
    }
    public void remove(Category category) {
        bllManager.remove(category);
    }
    
    
}
