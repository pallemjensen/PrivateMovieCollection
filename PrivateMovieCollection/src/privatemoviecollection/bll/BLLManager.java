/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll;

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

    public movie createMovie(String movieName, double imdbRating, double privateRating, String fileLink, long lastView) {
    movie newMovie = movieDAO.createMovie(movieName, imdbRating, privateRating, fileLink, lastView);
    return newMovie;
    }
    
}
