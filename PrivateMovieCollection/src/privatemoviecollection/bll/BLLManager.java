/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public void remove(Movie movie) throws PMCException{
        movieDAO.remove(movie);
    }
    
    public void remove(Category category) throws PMCException{
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
            long twoYears = 6307200000000l;
            final double i = 6;
            for (Movie allMovy : allMovies) {
                if ((todayMilli - (allMovy.getLastView()) > twoYears) || ((allMovy.getPrivateRating() < i))) {
                    oldAndBadMovies.add(allMovy);
                }
            }
            return allMovies;
    }
    
    public void addCategoryToMovie(ArrayList<Integer> list) throws PMCException{
    mic.addCategoryToMovie(list);
    }
}
