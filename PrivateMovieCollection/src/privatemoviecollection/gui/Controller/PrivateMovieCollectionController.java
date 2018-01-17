/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
     *
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
        chekForOldOrBadMovies();
        try {
            pmcModel.loadMovies();
            pmcModel.loadCategories();
        } catch (PMCException ex) {
            exceptionHandler(ex);
        }
        
    }

    @FXML
    private void btnAddCategory(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/View/NewCategory.fxml"));
            Parent root = (Parent) fxmlLoader1.load();
            NewCategoryController ncc = fxmlLoader1.getController();
            ncc.setUp(pmcModel);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddMovie(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/View/AddMovie.fxml"));
            Parent root = (Parent) fxmlLoader1.load();
            AddMovieController amc = fxmlLoader1.getController();
            amc.setUp(pmcModel);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnDeleteMovie(ActionEvent event) {
        try {
            Movie movie = TVMovies.getSelectionModel().getSelectedItem();
            pmcModel.remove(movie);
            pmcModel.loadMovies();
        } catch (PMCException ex) {
            exceptionHandler(ex);
        }

    }

    @FXML
    private void btnEditMovieRating(ActionEvent event) throws PMCException {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/View/EditMovieRating.fxml"));
            Parent root = (Parent) fxmlLoader1.load();
            EditMovieRatingController emrc = fxmlLoader1.getController();
            emrc.setUp(pmcModel, TVMovies.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
            pmcModel.loadMovies();
        } catch (IOException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnPlay(ActionEvent event) {
        if (TVMovies.getSelectionModel().getSelectedItem() != null) {
            try {
                pmcModel.updateLastView(TVMovies.getSelectionModel().getSelectedItem());
                File file = new File(TVMovies.getSelectionModel().getSelectedItem().getFileLink());
                desktop.open(file);
            } catch (PMCException ex) {
                exceptionHandler(ex);
            } catch (IOException ex) {
                Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void btnDeleteCategory(ActionEvent event) {
        try {
            Category category = TVCategories.getSelectionModel().getSelectedItem();
            pmcModel.remove(category);
            pmcModel.loadCategories();
        } catch (PMCException ex) {
            exceptionHandler(ex);
        }
    }

    @FXML
    private void btnClearFilter(ActionEvent event) {
        txtTitleFilter.setText("");
        txtImdbFilter.setText("0.0");
        TVMovies.setItems(pmcModel.getMovies());
    }

    @FXML
    public void btnLoadMovies(ActionEvent event) {
        try {
            pmcModel.loadMovies();
        } catch (PMCException ex) {
            exceptionHandler(ex);
        }
    }

    public void chekForOldOrBadMovies() {
        try {
            List<Movie> oldAndBadMovies = pmcModel.chekForOldOrBadMovies();
            if (!oldAndBadMovies.isEmpty()) {
                FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/View/MovieIsTooOldOrTooLowRating.fxml"));
                Parent root = (Parent) fxmlLoader1.load();
                MovieIsTooOldOrTooLowRatingController mist = fxmlLoader1.getController();
                mist.setUp(oldAndBadMovies, pmcModel);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        } catch (PMCException ex) {
            exceptionHandler(ex);
        } catch (IOException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnLoadCategories(ActionEvent event) {
        try {
            pmcModel.loadCategories();
        } catch (PMCException ex) {
            exceptionHandler(ex);
        }
    }

    @FXML
    private void btnFilterOnMovieTitle(ActionEvent event) {
        try {
//            String filter = txtTitleFilter.getText();
//            ObservableList<Movie> filteredMovies = pmcModel.filterOnTitle(filter);
//            TVMovies.setItems(filteredMovies);
            TVMovies.setItems(pmcModel.filterOnTitle(txtTitleFilter.getText()));
        } catch (PMCException ex) {
            exceptionHandler(ex);
        }
    }

    @FXML
    private void btnFilterOnImdbRating(ActionEvent event) {
        try {
//            Double filter = Double.valueOf(txtImdbFilter.getText());
//            ObservableList<Movie> filteredMovies = pmcModel.filterOnRating(filter);
//            TVMovies.setItems(filteredMovies);
            TVMovies.setItems(pmcModel.filterOnRating(Double.valueOf(txtImdbFilter.getText())));
        } catch (PMCException ex) {
            exceptionHandler(ex);
        }
    }

    @FXML
    private void btnExit(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void btnAddCatToMovie(ActionEvent event) throws IOException {
        
        if(!TVMovies.getSelectionModel().isEmpty()) {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/View/AddCategoryToMovie.fxml"));
            Parent root = (Parent) fxmlLoader1.load();
            AddCategoryToMovieController actmc = fxmlLoader1.getController();
            actmc.setUp(pmcModel);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        
//        ArrayList<Integer> movieCatList = new ArrayList<>();
//        int movieId = TVMovies.getSelectionModel().getSelectedItem().getId();
//        int categoryId = TVCategories.getSelectionModel().getSelectedItem().getId();
//        movieCatList.add(categoryId);
//        movieCatList.add(movieId);
//        try {
//            pmcModel.addMovieToCategory(movieCatList);
//        } catch (PMCException ex) {
//            exceptionHandler(ex);
//        }
    }

    static void exceptionHandler(Exception ex) {
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

   @FXML
    private void btnShowMoviesByCategory(ActionEvent event) {
        ObservableList movCat = FXCollections.observableArrayList();
     int categoryId = TVCategories.getSelectionModel().getSelectedItem().getId();
        try {
            movCat = pmcModel.getCategoriesToMovie(categoryId);
            TVMovies.setItems(movCat);
        } catch (PMCException ex) {
            Logger.getLogger(PrivateMovieCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            exceptionHandler(ex);
        }
    }  
    
    
    @FXML
    private void btnsearchOnImdb(ActionEvent event) {
        String movieName = TVMovies.getSelectionModel().getSelectedItem().getMovieName();
        String urlMap = "http://www.imdb.com/find?ref_=nv_sr_fn&q=" + movieName.replace(" ", "%20") + "&s=all";
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(urlMap));
            } catch (IOException | URISyntaxException e) {
                exceptionHandler(e);
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + urlMap);
            } catch (IOException e) {
                exceptionHandler(e);
            }
        }
    }
}   
