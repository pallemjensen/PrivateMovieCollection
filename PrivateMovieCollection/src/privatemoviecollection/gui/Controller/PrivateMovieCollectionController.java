/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.be.PMCException;
import privatemoviecollection.gui.Model.PMCModel;

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

    private final Desktop desktop = Desktop.getDesktop();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
        txtImdbFilter.setText("0.0");
    }

    @FXML
    private void btnAddCategory(ActionEvent event) {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/View/NewCategory.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader1.load();
        } catch (IOException ex) {
        Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        PMCException pmce = new PMCException("IO Error - wrong user input");
        exceptionHandler(pmce);}
        NewCategoryController ncc = fxmlLoader1.getController();
        ncc.setUp(pmcModel);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnAddMovie(ActionEvent event) {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/View/AddMovie.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader1.load();
        } catch (IOException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            PMCException pmce = new PMCException("IO Error - wrong user input");
            exceptionHandler(pmce);
        }
        AddMovieController amc = fxmlLoader1.getController();
        amc.setUp(pmcModel);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnDeleteMovie(ActionEvent event)  {
    Movie movie = TVMovies.getSelectionModel().getSelectedItem();
        try {
            pmcModel.remove(movie);
        } catch (PMCException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            exceptionHandler(ex);
        }
        try {
            pmcModel.loadMovies();
        } catch (PMCException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            exceptionHandler(ex);
        }
    }

    @FXML
    private void btnEditMovieRating(ActionEvent event) throws PMCException{
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/View/EditMovieRating.fxml"));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader1.load();
        } catch (IOException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            PMCException pmce = new PMCException("IO Error - wrong user input - use a number - example '8.0'.");
            exceptionHandler(pmce);
        }
        EditMovieRatingController emrc = fxmlLoader1.getController();
        emrc.setUp(pmcModel, TVMovies.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        pmcModel.loadMovies();
    }

    @FXML
    private void btnPlay(ActionEvent event)  {
        if(TVMovies.getSelectionModel().getSelectedItem() != null){
            try {
                pmcModel.updateLastView(TVMovies.getSelectionModel().getSelectedItem());
            } catch (PMCException ex) {
                Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
            File file = new File(TVMovies.getSelectionModel().getSelectedItem().getFileLink());
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
                PMCException pmce = new PMCException("IO Error - filepath is wrong.");
                exceptionHandler(pmce);
            }
        }
    }

    @FXML
    private void btnDeleteCategory(ActionEvent event) {
        Category category = TVCategories.getSelectionModel().getSelectedItem();
        try {
            pmcModel.remove(category);
        } catch (PMCException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            exceptionHandler(ex);
        }
        try {
            pmcModel.loadCategories();
        } catch (PMCException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            exceptionHandler(ex);
        }
    }


    @FXML
    private void btnClearFilter(ActionEvent event) {
        txtTitleFilter.setText("");
        txtImdbFilter.setText("0.0");
        try {
            pmcModel.loadMovies();
        } catch (PMCException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            exceptionHandler(ex);
        }
        TVMovies.setItems(pmcModel.getMovies());
    }

    @FXML
    public void btnLoadMovies(ActionEvent event) throws PMCException{
        List<Movie> allMovies = pmcModel.getAllMovies();
        List<Movie> oldAndBadMovies = new ArrayList<>();
        Date today = new Date();
        long todayMilli = today.getTime();
        long twoYears = 6307200000000l;
        final double i = 6;
        boolean b = false;
        for (Movie allMovy : allMovies) {
            if ((todayMilli-(allMovy.getLastView()) > twoYears) || ((allMovy.getPrivateRating() < i)) )
                    {
            oldAndBadMovies.add(allMovy);
            b = true;
                    }
        }
        if (b){
            pmcModel.loadMovies();
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/View/MovieIsTooOldOrTooLowRating.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader1.load();
            } catch (IOException ex) {
                Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
                PMCException pmce = new PMCException("IO Error - filepath is wrong.");
                exceptionHandler(pmce);
            }
            MovieIsTooOldOrTooLowRatingController mist = fxmlLoader1.getController();
            mist.setUp(oldAndBadMovies);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
            else
                pmcModel.loadMovies();      
}
    

    @FXML
    private void btnLoadCategories(ActionEvent event) {
        try {
            pmcModel.loadCategories();
        } catch (PMCException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            exceptionHandler(ex);
        }
    }

    @FXML
    private void btnFilterOnMovieTitle(ActionEvent event) throws PMCException {
        List<Movie> allMovies = pmcModel.getAllMovies();
        ObservableList<Movie> filteredMovies = FXCollections.observableArrayList();
        String filter = txtTitleFilter.getText();
        for (Movie allMovy : allMovies) {
        if (allMovy.getMovieName().toLowerCase().trim().contains(filter.toLowerCase().trim()) == true)
        {
            filteredMovies.add(allMovy);   
        }
        TVMovies.setItems(filteredMovies);
        }
    }

    @FXML
    private void btnFilterOnImdbRating(ActionEvent event) throws PMCException {
    List<Movie> allMovies = pmcModel.getAllMovies(); 
    ObservableList<Movie> filteredMovies = FXCollections.observableArrayList();
    Double filter = Double.valueOf(txtImdbFilter.getText());
        System.out.println("" + filter);
        for (Movie filteredMovy : allMovies) {
            if (filteredMovy.getImdbRating() >= filter)
            {
              filteredMovies.add(filteredMovy);
            }
         TVMovies.setItems(filteredMovies);   
        }
    }

    @FXML
    private void btnExit(ActionEvent event) {
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnAddCatToMovie(ActionEvent event) {
    }
    
    @FXML
    static void exceptionHandler(Exception ex) 
    {
        try {
            String errorMsg = ex.getMessage();
            FXMLLoader fxmlLoader1 = new FXMLLoader(ExceptionMessengerController.class.getResource("/privatemoviecollection/gui/View/exceptionMessenger.fxml"));
            Parent root = (Parent) fxmlLoader1.load();
            ExceptionMessengerController emc = fxmlLoader1.getController();
            emc.setErrorMsg(errorMsg);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex1) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }
}
