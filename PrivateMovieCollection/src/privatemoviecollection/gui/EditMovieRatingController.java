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
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class EditMovieRatingController implements Initializable {

    @FXML
    private TextField txtRating;
    
    private PMCModel pmcModel;
    private Movie movie;
    private int id;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setUp(PMCModel model) {
       pmcModel = model;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setValue(Double value){
        txtRating.setText(value.toString());
    }
    
    public void setMovie(Movie movie){
        this.movie = movie;
    }
    
    @FXML
    private void btnSubmit(ActionEvent event) {
        double value = Double.parseDouble(txtRating.getText());
        pmcModel.editPersonalRating(id, value);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

}
