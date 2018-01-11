/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.be;

/**
 *
 * @author pmj
 */
public class Category {

    private String categoryName;
    private int id;

//Constructor for Category object with paramter category_name

    /**
     *
     * @param categoryName
     */
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    
    /**
     *
     * @param id
     * @param categoryName
     */
    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    /**
     *
     */
    public Category() {   
    }

    /**
     *
     * @return
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     *
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
}
