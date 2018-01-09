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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import privatemoviecollection.be.category;
import privatemoviecollection.be.movie;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class PrivateMovieCollectionController implements Initializable {

    @FXML
    private TableView<category> TVCategories;
    @FXML
    private TableColumn<category, String> categoryColumn;
    @FXML
    private TableView<movie> TVMovies;
    @FXML
    private TableColumn<movie, String> movieTitleColumn;
    @FXML
    private TableColumn<movie, Double> movieImdbColumn;
    @FXML
    private TableColumn<movie, Double> movieUserRatingColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       categoryColumn.setCellValueFactory(
                new PropertyValueFactory("categoryName"));
        movieTitleColumn.setCellValueFactory(
                new PropertyValueFactory("movieName"));
        movieImdbColumn.setCellValueFactory(
                new PropertyValueFactory("imdbRating"));
        movieUserRatingColumn.setCellValueFactory(
                new PropertyValueFactory("privateRating"));

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

    @FXML
    private void btnFilter(ActionEvent event) {
    }

    @FXML
    private void btnClearFilter(ActionEvent event) {
    }
    
}
