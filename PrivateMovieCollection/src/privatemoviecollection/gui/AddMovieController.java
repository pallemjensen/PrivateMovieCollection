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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pmj
 */
public class AddMovieController implements Initializable {

    @FXML
    private TextField txtMovieTitle;
    @FXML
    private TextField txtMovieImdbRating;
    @FXML
    private TextField txtMovieCategory;
    @FXML
    private TextField txtMoviePersonalRating;
    @FXML
    private TextField txtMovieFilePath;
    @FXML
    private Button btnChooseMovie;
    @FXML
    private Button btnSaveMovie;
    @FXML
    private Button btnCancelAddMovie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnChooseMovie(ActionEvent event) {
    }

    @FXML
    private void btnSaveMovie(ActionEvent event) {
    }

    @FXML
    private void btnCancelAddMovie(ActionEvent event) {
    }
    
}
