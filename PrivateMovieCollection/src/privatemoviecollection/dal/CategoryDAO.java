/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.PMCException;

/**
 *Our Category class where we manage our calls to the DB
 *We can save, load and delete categories.
 */
public class CategoryDAO {

    ConnectionManager cm = new ConnectionManager();

 /**
  * 
 *Create a new category and return the new object
     * @param categoryName
     * @return
     * @throws privatemoviecollection.be.PMCException
 */
    public Category createCategory(String categoryName) throws PMCException {
        
        try(Connection con = cm.getConnection()){
        String sql = "INSERT INTO Category VALUES(?);";
        
        PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PMCException("SQLException. Could not create a new category. Prepared statement.");
            }
            try {
                stmt.setString(1, categoryName);
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PMCException("SQLException. Could not create a new category. Categoryname.");
            }
        
            try {
                if (stmt.executeUpdate() == 1)
                {
                    ResultSet rs = stmt.getGeneratedKeys();
                    rs.next();
                    int id = rs.getInt(1);
                    Category newCategory = new Category(id, categoryName);
                    return newCategory;
                }   } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new PMCException("SQLException. Could not create a new category. Return new category.");
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLServerException. Could not create a new category. Categoryname.");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLException. Could not connect to server. Check internet connection.");
        }
        return null;
    }
    
    
 /**
 *We load our categories
     * @return
     * @throws privatemoviecollection.be.PMCException
 */
    public List<Category> getAllCategories() throws PMCException {
        List<Category> categories = new ArrayList<>();

        try (Connection con = cm.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Category");

            while (rs.next()) {
                Category currentCategory = new Category();
                currentCategory.setId(rs.getInt("category_id"));
                currentCategory.setCategoryName(rs.getString("category_name"));
                categories.add(currentCategory);
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLServerException. Could not connect to server. Check internet connection.");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
             throw new PMCException("SQLException. Could not load categories.");
        }
        return categories;
    }
    
 /**
 *Delete a category
     * @param category
     * @throws privatemoviecollection.be.PMCException
 */
    public void remove(Category category) throws PMCException{
        try (Connection con = cm.getConnection();) {
            Statement stmt1 = con.createStatement();
            stmt1.execute("DELETE FROM CatMovie WHERE cat_id="+ category.getId());
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM Category WHERE category_id="+category.getId());
        } catch (SQLServerException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLException. Could not connect to the database.");
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PMCException("SQLException. Could not delete the category.");
        }
    }
}