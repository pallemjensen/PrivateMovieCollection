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
import privatemoviecollection.be.category;
import privatemoviecollection.be.movie;

/**
 *
 * @author pmj
 */
public class CategoryDAO {

    ConnectionManager cm = new ConnectionManager();

    public category createCategory(String categoryName) throws SQLException {
        
        try(Connection con = cm.getConnection()){
        String sql = "INSERT INTO Category VALUES(?):";
        
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, categoryName);
        if (stmt.executeUpdate() == 1)
        {
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            category newCategory = new category(id, categoryName);
            return newCategory;
        }
        }
        return null;
    }
    
    public List<category> getAllCategories() throws SQLServerException, SQLException {
        List<category> categories = new ArrayList<>();

        try (Connection con = cm.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Category");

            while (rs.next()) {
                category currentCategory = new category();
                currentCategory.setcategoryName(rs.getString("category_name"));
                categories.add(currentCategory);
            }
        }
        return categories;
    }

}
