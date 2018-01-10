/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnConfirmWarning(ActionEvent event) {
    }

    void setUp(PMCModel pmcModel) {
    }     
}
