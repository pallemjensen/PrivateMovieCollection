/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.PMCException;
import static privatemoviecollection.gui.Controller.PrivateMovieCollectionController.exceptionHandler;
import privatemoviecollection.gui.Model.PMCModel;

/**
 * FXML Controller class
 *
 * @author pmj
 */
public class MovieIsTooOldOrTooLowRatingController implements Initializable {
    
    @FXML
    private TableView<Movie> TVOldOrBadMovies;
    @FXML
    private TableColumn<Movie, String> oldOrBadColoumn;
    @FXML
    private TableColumn<Movie, String> lastViewColumn;
    @FXML
    private TableColumn<Movie, String> ratingColumn;
    private PMCModel model;
    private final ObservableList<Movie> movies
            = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    oldOrBadColoumn.setCellValueFactory(
                new PropertyValueFactory("movieName"));
    lastViewColumn.setCellValueFactory(
                new PropertyValueFactory("dateLastviewed"));
    ratingColumn.setCellValueFactory(
                new PropertyValueFactory("privateRating"));
    TVOldOrBadMovies.setItems(movies);
    }    

    @FXML
    private void btnConfirmWarning(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    public void setUp(List<Movie> movielist, PMCModel model) {
        this.model = model;
        movies.addAll(movielist);
    }

    @FXML
    private void btnDeleteMovie(ActionEvent event) {
        if(!TVOldOrBadMovies.getSelectionModel().isEmpty()){
            try {
                model.remove(TVOldOrBadMovies.getSelectionModel().getSelectedItem());
                movies.remove(TVOldOrBadMovies.getSelectionModel().getSelectedItem());
            } catch (PMCException ex) {
                exceptionHandler(ex);
            }
        }
    }
}
