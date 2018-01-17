/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.Model;

import java.util.ArrayList;
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

    /**
     * 
     * @param movieName
     * @param imdbRating
     * @param privateRating
     * @param fileLink
     * @param lastView
     * @throws PMCException 
     */
    public void addNewMovie(String movieName, double imdbRating, double privateRating, String fileLink, long lastView) throws PMCException {
        Movie newMovie = bllManager.createMovie(movieName, imdbRating, privateRating, fileLink, lastView);
        movies.add(newMovie);
    }
    /**
     * 
     * @param categoryName
     * @throws PMCException 
     */
    public void createCategory(String categoryName) throws PMCException {
        Category newCategory = bllManager.createCategory(categoryName);
        categories.add(newCategory);
    }
    /**
     * 
     * @return
     * @throws PMCException 
     */
    public List<Movie> getAllMovies() throws PMCException {
        return bllManager.getAllMovies();
    }
    /**
     * 
     * @return
     * @throws PMCException 
     */
    public List<Category> getAllCategories() throws PMCException {
        return bllManager.getAllCategories();
    }
    /**
     * 
     * @throws PMCException 
     */
    public void loadMovies() throws PMCException {
        List<Movie> loadedMovies = bllManager.getAllMovies();
        movies.clear();
        movies.addAll(loadedMovies);
    }
    /**
     * 
     * @throws PMCException 
     */
    public void loadCategories() throws PMCException {
        List<Category> loadedCategories = bllManager.getAllCategories();
        categories.clear();
        categories.addAll(loadedCategories);
    }
    /**
     * 
     * @return 
     */
    public ObservableList<Movie> getMovies() {
        return movies;
    }
    /**
     * 
     * @return 
     */
    public ObservableList<Category> getCategories() {
        return categories;
    }
    /**
     * 
     * @param movie
     * @throws PMCException 
     */
    public void remove(Movie movie) throws PMCException {
        bllManager.remove(movie);
    }
    /**
     * 
     * @param category
     * @throws PMCException 
     */
    public void remove(Category category) throws PMCException {
        bllManager.remove(category);
    }
    /**
     * 
     * @param id
     * @param value
     * @throws PMCException 
     */
    public void editPersonalRating(int id, double value) throws PMCException {
        bllManager.editPersonalRating(id, value);

    }
    /**
     * 
     * @param movie
     * @throws PMCException 
     */
    public void updateLastView(Movie movie) throws PMCException {
        bllManager.updateLastView(movie);
    }
    /**
     * 
     * @param name
     * @return
     * @throws PMCException 
     */
    public boolean doesMovieAlreadyExist(String name) throws PMCException {
        return bllManager.doesMovieAlreadyExist(name);
    }
    /**
     * 
     * @return
     * @throws PMCException 
     */
    public List<Movie> chekForOldOrBadMovies() throws PMCException {
        return bllManager.chekForOldOrBadMovies();
    }
    /**
     * 
     * @param list
     * @throws PMCException 
     */
    public void addMovieToCategory(ArrayList<Integer> list) throws PMCException {
        bllManager.addCategoryToMovie(list);
    }
    /**
     * 
     * @param category
     * @return
     * @throws PMCException 
     */
    public ArrayList<Integer> getCategoriesToMovie(int category) throws PMCException {
        return bllManager.getCategoriesToMovie(category);
    }
    /**
     * 
     * @param filter
     * @return
     * @throws PMCException 
     */
    public ObservableList<Movie> filterOnTitle(String filter) throws PMCException {
        return bllManager.filterOnTitle(filter);
    }
    /**
     * 
     * @param filter
     * @return
     * @throws PMCException 
     */
    public ObservableList<Movie> filterOnRating(Double filter) throws PMCException {
        return bllManager.filterOnRating(filter);
    }
}
