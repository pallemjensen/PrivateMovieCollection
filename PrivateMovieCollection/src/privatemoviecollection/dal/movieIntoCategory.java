/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import privatemoviecollection.be.PMCException;

/**
 *
 * @author pmj
 */
public class movieIntoCategory {
    
    ConnectionManager cm = new ConnectionManager();
    
    public void addCategoryToMovie(ArrayList<Integer> list) throws PMCException {
        int categoryId = list.get(1);
        int movieId = list.get(1);
        System.out.println("" + categoryId );
        System.out.println("" + movieId );
        try(Connection con = cm.getConnection()){
        String sql = "INSERT INTO CatMovie VALUES("+ categoryId +", " + movieId + ");";
        
         } catch (SQLServerException ex) {
             Logger.getLogger(movieIntoCategory.class.getName()).log(Level.SEVERE, null, ex);
             throw new PMCException("Could not connect to database.");
        } catch (SQLException ex) {
            Logger.getLogger(movieIntoCategory.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("Could not add category to movie.");
        }
}
}
