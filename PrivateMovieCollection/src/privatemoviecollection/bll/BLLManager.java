/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.PMCException;
import privatemoviecollection.dal.CategoryDAO;
import privatemoviecollection.dal.MovieDAO;
import privatemoviecollection.dal.movieIntoCategory;

/**
 *
 * @author pmj
 */
public class BLLManager {

    MovieDAO movieDAO = new MovieDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    movieIntoCategory mic = new movieIntoCategory();

    public Movie createMovie(String movieName, double imdbRating, double privateRating, String fileLink, long lastView) throws PMCException {
        Movie newMovie = movieDAO.createMovie(movieName, imdbRating, privateRating, fileLink, lastView);
        return newMovie;
    }

    public List<Movie> getAllMovies() throws PMCException {
        return movieDAO.getAllMovies();
    }

    public Category createCategory(String categoryName) throws PMCException {
        Category newCategory = categoryDAO.createCategory(categoryName);
        return newCategory;
    }

    public List<Category> getAllCategories() throws PMCException {
        return categoryDAO.getAllCategories();
    }

    public void remove(Movie movie) throws PMCException {
        movieDAO.remove(movie);
    }

    public void remove(Category category) throws PMCException {
        categoryDAO.remove(category);
    }

    public void editPersonalRating(int id, double value) throws PMCException {
        movieDAO.editPersonalRating(id, value);
    }

    public void updateLastView(Movie movie) throws PMCException {
        Date date = new Date();
        long newView = date.getTime();
        movieDAO.updateLastView(movie, newView);
    }

    public boolean doesMovieAlreadyExist(String name) throws PMCException {
        List<Movie> all = movieDAO.getAllMovies();
        boolean b = false;
        for (Movie movie : all) {
            if (name.trim().equalsIgnoreCase(movie.getMovieName().trim())) {
                b = true;
                break;
            }
        }
        return b;
    }

    public List<Movie> chekForOldOrBadMovies() throws PMCException {
        List<Movie> allMovies = movieDAO.getAllMovies();
        List<Movie> oldAndBadMovies = new ArrayList<>();
        Date today = new Date();
        long todayMilli = today.getTime();
        long twoYearsMilli = 6307200000l;
        final double minimumRating = 6.0;
        for (Movie allMovy : allMovies) {
            if ((todayMilli - (allMovy.getLastView()) > twoYearsMilli) || ((allMovy.getPrivateRating() < minimumRating))) {
                oldAndBadMovies.add(allMovy);
            }
        }
        return oldAndBadMovies;
    }

    public void addCategoryToMovie(ArrayList<Integer> list) throws PMCException {
        mic.addCategoryToMovie(list);
    }

    public ObservableList<Movie> getCategoriesToMovie(int categoryId) throws PMCException {
        ObservableList<Integer> catMoviesInt = mic.getCategoriesToMovie(categoryId);
        ObservableList<Movie> catMovies = FXCollections.observableArrayList();
        Movie movie = new Movie();
        for (int i = 0; i <= catMoviesInt.size(); i++){
        movie.setId(catMoviesInt.get(i));
        catMovies.add(movie);
    }
       return catMovies;  
    }

    public ObservableList<Movie> filterOnTitle(String filter) throws PMCException {
        List<Movie> allMovies = movieDAO.getAllMovies();
        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList();
        for (Movie movy : allMovies) {
            if(movy.getMovieName().toUpperCase().contains(filter.toUpperCase()))
                filteredMovies.add(movy);
        }
        return filteredMovies;
    }

    public ObservableList<Movie> filterOnRating(Double filter) throws PMCException {
        List<Movie> allMovies = movieDAO.getAllMovies();
        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList();
        for (Movie allMovy : allMovies) {
            if(allMovy.getImdbRating() >= filter)
                filteredMovies.add(allMovy);
        }
        return filteredMovies;
    }
}
