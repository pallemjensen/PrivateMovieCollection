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
import privatemoviecollection.dal.MovieIntoCategoryDAO;

/**
 *
 * @author pmj
 */
public class BLLManager {

    MovieDAO movieDAO = new MovieDAO();
    CategoryDAO categoryDAO = new CategoryDAO();
    MovieIntoCategoryDAO mic = new MovieIntoCategoryDAO();

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
//        mic.removeAllCatOnMovie(movie.getId());
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

    public void addCategoryToMovie(int categoryId, int movieId) throws PMCException {
        mic.addCategoryToMovie(categoryId, movieId);
    }

    public ObservableList<Movie> getCategoriesToMovie(int categoryId) throws PMCException {
        return mic.getCategoriesToMovie(categoryId);
    }

    public ObservableList<Movie> filterOnTitle(String filter) throws PMCException {
        List<Movie> allMovies = movieDAO.getAllMovies();
        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList();
        for (Movie movy : allMovies) {
            if (movy.getMovieName().toUpperCase().contains(filter.toUpperCase())) {
                filteredMovies.add(movy);
            }
        }
        return filteredMovies;
    }

    public ObservableList<Movie> filterOnRating(Double filter) throws PMCException {
        List<Movie> allMovies = movieDAO.getAllMovies();
        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList();
        for (Movie allMovy : allMovies) {
            if (allMovy.getImdbRating() >= filter) {
                filteredMovies.add(allMovy);
            }
        }
        return filteredMovies;
    }

    public ObservableList<Movie> filter(ObservableList<Movie> inList, String word, Double filter) {
        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList();
        for (Movie movie : inList) {
            if (movie.getMovieName().toUpperCase().contains(word.toUpperCase()) && movie.getImdbRating() >= filter) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

}
