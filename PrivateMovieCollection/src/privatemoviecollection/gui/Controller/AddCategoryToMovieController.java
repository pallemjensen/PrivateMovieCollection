/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.Controller;

import java.net.URL;
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
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.PMCException;
import static privatemoviecollection.gui.Controller.PrivateMovieCollectionController.exceptionHandler;
import privatemoviecollection.gui.Model.PMCModel;

/**
 * FXML Controller class
 *
 * @author Anders
 */
public class AddCategoryToMovieController implements Initializable {

    private PMCModel pmcModel;
    private Movie pmcSelectedMovie;
    private ObservableList<Category> categories
            = FXCollections.observableArrayList();

    @FXML
    private TableView<Category> TVCategories;

    @FXML
    private TableColumn<Category, String> categoryColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryColumn.setCellValueFactory(
                new PropertyValueFactory("categoryName"));
        TVCategories.setItems(categories);
    }
    /**
     * Saves category to movie
     * @param event 
     */
    @FXML
    private void btnSave(ActionEvent event) {
        if (!TVCategories.getSelectionModel().isEmpty()) {
            try {
                pmcModel.addCategoryToMovie(TVCategories.getSelectionModel().getSelectedItem().getId(), pmcSelectedMovie.getId());
            } catch (PMCException ex) {
                exceptionHandler(ex);
            }
            categories.remove(TVCategories.getSelectionModel().getSelectedItem());
//            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
    /**
     * 
     * @param model
     * @param selectedMovie 
     */
    public void setUp(PMCModel model, Movie selectedMovie) {
        pmcModel = model;
        pmcSelectedMovie = selectedMovie;
        categories.addAll(pmcModel.getCategories());
    }

}
