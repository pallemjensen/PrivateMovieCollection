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
     * @throws java.sql.SQLException
 */
    public Category createCategory(String categoryName) throws SQLException {
        
        try(Connection con = cm.getConnection()){
        String sql = "INSERT INTO Category VALUES(?);";
        
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, categoryName);
        
        if (stmt.executeUpdate() == 1)
        {
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            Category newCategory = new Category(id, categoryName);
            return newCategory;
        }
        }
        return null;
    }
    
    
 /**
 *We load our categories
     * @return 
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException
 */
    public List<Category> getAllCategories() throws SQLServerException, SQLException {
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
        }
        return categories;
    }
    
 /**
 *Delete a category
     * @param category
 */
    public void remove(Category category){
        try (Connection con = cm.getConnection();) {
            Statement stmt = con.createStatement();
            stmt.execute("DELETE FROM Category WHERE category_id="+category.getId());
        } catch (SQLServerException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}