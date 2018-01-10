/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class PrivateMovieCollectionController implements Initializable {

    private final PMCModel pmcModel = new PMCModel();

    @FXML
    private TableView<Category> TVCategories;
    @FXML
    private TableColumn<Category, String> categoryColumn;
    @FXML
    private TableView<Movie> TVMovies;
    @FXML
    private TableColumn<Movie, String> movieTitleColumn;
    @FXML
    private TableColumn<Movie, Double> movieImdbColumn;
    @FXML
    private TableColumn<Movie, Double> movieUserRatingColumn;
    @FXML
    private TextField txtTitleFilter;
    @FXML
    private TextField txtImdbFilter;

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
        TVMovies.setItems(pmcModel.getMovies());
        TVCategories.setItems(pmcModel.getCategories());
    }

    @FXML
    private void btnAddCategory(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("NewCategory.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        NewCategoryController ncc = fxmlLoader1.getController();
        ncc.setUp(pmcModel);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnAddMovie(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("addMovie.fxml"));
        Parent root = (Parent) fxmlLoader1.load();
        AddMovieController amc = fxmlLoader1.getController();
        amc.setUp(pmcModel);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnDeleteMovie(ActionEvent event) throws SQLException {
    Movie movie = TVMovies.getSelectionModel().getSelectedItem();
    pmcModel.remove(movie);
    pmcModel.loadMovies();
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
    private void btnDeleteCategory(ActionEvent event) throws SQLException {
        Category category = TVCategories.getSelectionModel().getSelectedItem();
        pmcModel.remove(category);
        System.out.println("" + category.getId());
        pmcModel.loadCategories();
    }


    @FXML
    private void btnClearFilter(ActionEvent event) {
    }

    @FXML
    private void btnLoadMovies(ActionEvent event) throws SQLException {
        pmcModel.loadMovies();
    }

    @FXML
    private void btnLoadCategories(ActionEvent event) throws SQLException {
        pmcModel.loadCategories();
    }

    @FXML
    private void btnFilterOnMovieTitle(ActionEvent event) {
    }

    @FXML
    private void btnFilterOnImdbRating(ActionEvent event) {
    }
}
