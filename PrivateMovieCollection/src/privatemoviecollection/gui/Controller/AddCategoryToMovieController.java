/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.Controller;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    @FXML
    private TableView<Category> TVCategories;
    @FXML
    private TableColumn<Category,String> categoryName;
    @FXML
    private TableColumn<Category, String> categoryColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryColumn.setCellValueFactory(
                new PropertyValueFactory("categoryName"));
        TVCategories.setItems(pmcModel.getCategories());
        try {
            pmcModel.loadCategories();
        } catch (PMCException ex) {
            exceptionHandler(ex);
        }
    }    

    @FXML
    private void btnSave(ActionEvent event) {
        
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
    
    public void setUp(PMCModel model, Movie selectedMovie) {
        pmcModel = model;
        pmcSelectedMovie = selectedMovie;
    }
    
}
