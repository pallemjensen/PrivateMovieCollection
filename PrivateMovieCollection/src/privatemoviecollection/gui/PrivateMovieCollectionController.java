/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class PrivateMovieCollectionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSearch(ActionEvent event) {
    }

    @FXML
    private void btnClearSearch(ActionEvent event) {
    }

    @FXML
    private void btnAddCategory(ActionEvent event) {
    }

    @FXML
    private void btnAddMovie(ActionEvent event) {
    }

    @FXML
    private void btnDeleteMovie(ActionEvent event) {
    }

    @FXML
    private void btnEditMovieRating(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("EditMovieRating.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnPlay(ActionEvent event) {
    }

    @FXML
    private void btnDeleteCategory(ActionEvent event) {
    }
    
}
