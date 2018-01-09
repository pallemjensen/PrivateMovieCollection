/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
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
        // TODO
    }    

    @FXML
    private void btnChooseMovie(ActionEvent event) {
    FileChooser chooser = new FileChooser();
    chooser.getExtensionFilters().clear();
    FileChooser.ExtensionFilter filterMp4 = new FileChooser.ExtensionFilter("select your media(*.mp4)", "*.mp4");
    FileChooser.ExtensionFilter filterMpeg4 = new FileChooser.ExtensionFilter("select your media(*.mpeg4)", "*.mpeg4");
    chooser.getExtensionFilters().add(filterMp4);
    chooser.getExtensionFilters().add(filterMpeg4);
    File file = chooser.showOpenDialog(this.stage);
    newMoviePath = file.getAbsolutePath();
    txtMovieFilePath.setText(newMoviePath);
    }

    @FXML
    private void btnSaveMovie(ActionEvent event) {
    }

    @FXML
    private void btnCancelAddMovie(ActionEvent event) {
    }
    
    public void setUp(PMCModel model) {
       this.pmcModel = model;
    }
    
}
