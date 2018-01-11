/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;

/**
 * FXML Controller class
 * Class for choosing and creating movies 
 * @author pmj
 */
public class AddMovieController implements Initializable {
        
    private PMCModel pmcModel;
    private Stage stage;

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
    String newMoviePath = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtMovieImdbRating.setText("0.0");
        txtMoviePersonalRating.setText("0.0");
    }    

 /**
 * Choose a movie, get the path and showing the path.
 */
    @FXML
    private void btnChooseMovie(ActionEvent event) {
    FileChooser chooser = new FileChooser();
    chooser.getExtensionFilters().clear();
    FileChooser.ExtensionFilter filterMp4_mpeg4 = new FileChooser.ExtensionFilter("select media( .mp4 or .mpeg4)", "*.mp4", "*.mpeg4");
    chooser.getExtensionFilters().add(filterMp4_mpeg4);
    File file = chooser.showOpenDialog(this.stage);
    newMoviePath = file.getAbsolutePath();
    txtMovieFilePath.setText(newMoviePath);
    }

    
 /**
 *Create a movie after checking if it has the name
 */
    @FXML
    private void btnSaveMovie(ActionEvent event) throws SQLException, IOException {
    List<Movie> movies = pmcModel.getAllMovies();
    boolean b = false;
        for (Movie filterMovy : movies) {
            if(txtMovieTitle.getText().trim().equalsIgnoreCase(filterMovy.getMovieName().trim()) == true){
                b = true;
                break;
            }
        }
            if(b){
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("ErrorSameMovieName.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
        else{
        String movieName = txtMovieTitle.getText();
        String imdbRatingAsString = txtMovieImdbRating.getText();
        Double imdbRating = Double.valueOf(imdbRatingAsString);
        String privateRatingAsString = txtMoviePersonalRating.getText();
        Double privateRating = Double.valueOf(privateRatingAsString);
        String fileLink = newMoviePath;
        Date date = new Date();
        long lastView = date.getTime();
        pmcModel.addNewMovie(movieName, imdbRating, privateRating, fileLink, lastView);
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    }
    

    @FXML
    private void btnCancelAddMovie(ActionEvent event) {
    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    public void setUp(PMCModel model) {
       pmcModel = model;
    }
    
}
