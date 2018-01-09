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
import privatemoviecollection.be.category;
import privatemoviecollection.be.movie;
import privatemoviecollection.bll.BLLManager;

/**
 *
 * @author pmj
 */
public class PMCModel {

    private final BLLManager bllManager = new BLLManager();

    private final ObservableList<movie> movies
            = FXCollections.observableArrayList();

    private final ObservableList<category> categories
            = FXCollections.observableArrayList();

    public void addNewMovie(String movieName, double imdbRating, double privateRating, String fileLink, long lastView) {
        movie newMovie = bllManager.createMovie(movieName, imdbRating, privateRating, fileLink, lastView);    
        movies.add(newMovie);
    }

    public List<movie> getAllMovies() throws SQLServerException, SQLException {
        return bllManager.getAllMovies();
    }

    public void loadMovies() throws SQLException {
        List<movie> loadedMovies = bllManager.getAllMovies();
        movies.clear();
        movies.addAll(loadedMovies);
    }

    public ObservableList<movie> getMovies() {
        return movies;
    }

    public category createCategory(String categoryName) throws SQLException {
        return bllManager.createCategory(categoryName);
    }

    public List<category> getAllCategories() throws SQLServerException, SQLException {
        return bllManager.getAllCategories();
    }

    public void loadCategories() {
        try {
            List<category> loadedCategories = bllManager.getAllCategories();
            categories.clear();
            categories.addAll(loadedCategories);
        } catch (SQLException ex) {
            Logger.getLogger(PMCModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<category> getCategories(){
        return categories;
    }
}
