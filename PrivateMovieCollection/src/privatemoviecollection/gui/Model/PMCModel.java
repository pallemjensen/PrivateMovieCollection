/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.Model;

import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.PMCException;
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

    public void addNewMovie(String movieName, double imdbRating, double privateRating, String fileLink, long lastView) throws PMCException {
        Movie newMovie = bllManager.createMovie(movieName, imdbRating, privateRating, fileLink, lastView);
        movies.add(newMovie);
    }
    
    public Category createCategory(String categoryName) throws PMCException {
        Category newCategory = bllManager.createCategory(categoryName);
        return newCategory;
    }

    public List<Movie> getAllMovies() throws PMCException {
        return bllManager.getAllMovies(); 
    }
    
     public List<Category> getAllCategories() throws PMCException {
        return bllManager.getAllCategories();
    }

    public void loadMovies() throws PMCException {
        List<Movie> loadedMovies = bllManager.getAllMovies();
        movies.clear();
        movies.addAll(loadedMovies);
    }
    
     public void loadCategories() throws PMCException {
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

    public void remove(Movie movie) throws PMCException {
        bllManager.remove(movie);
    }
    public void remove(Category category) throws PMCException {
        bllManager.remove(category);
    }

    public void editPersonalRating(int id, double value) throws PMCException {
        bllManager.editPersonalRating(id, value);
                
    }
    
    public void updateLastView(Movie movie) throws PMCException{
        Date date = new Date();
        long newView = date.getTime();
        bllManager.updateLastView(movie, newView);
    }
    
    public boolean doesMovieAlreadyExist(String name) throws PMCException{
        return bllManager.doesMovieAlreadyExist(name);
    }
}
