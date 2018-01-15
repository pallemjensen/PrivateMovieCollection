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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import privatemoviecollection.be.PMCException;
import privatemoviecollection.gui.Model.PMCModel;
import static privatemoviecollection.gui.Controller.PrivateMovieCollectionController.exceptionHandler;

/**
 * FXML Controller class
 *
 * @author palle
 */
public class NewCategoryController implements Initializable {

    private PMCModel pmcModel;
    @FXML
    private TextField txtAddNewCategory;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSaveNewCategory(ActionEvent event) {
    String categoryName = txtAddNewCategory.getText();
        try {
            pmcModel.createCategory(categoryName);
        } catch (PMCException ex) {
            exceptionHandler(ex);
        }
     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();   
    }

    @FXML
    private void btnCancelNewCategory(ActionEvent event) {
   ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void setUp(PMCModel model) {
        pmcModel = model;
    }
}
