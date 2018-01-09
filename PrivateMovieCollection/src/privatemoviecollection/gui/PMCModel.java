/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import privatemoviecollection.be.category;
import privatemoviecollection.be.movie;
import privatemoviecollection.bll.BLLManager;
/**
 *
 * @author pmj
 */
public class PMCModel {
    
    private final BLLManager bllManager = new BLLManager();

    private final ObservableList<movie> movies
            = FXCollections.observableArrayList();
    
    private final ObservableList<category> categories
            = FXCollections.observableArrayList();
    
}
