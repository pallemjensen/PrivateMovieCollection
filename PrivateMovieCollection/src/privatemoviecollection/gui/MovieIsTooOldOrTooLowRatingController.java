/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
    TVOldOrBadMovies.setItems(movies);
    }    

    @FXML
    private void btnConfirmWarning(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    public void setUp(List<Movie> movielist) {
       
        movies.addAll(movielist);
    }
}
