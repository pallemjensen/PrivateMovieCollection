/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSaveNewCategory(ActionEvent event) throws SQLException {
            String categoryName = txtAddNewCategory.getText();
            pmcModel.createCategory(categoryName);
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
