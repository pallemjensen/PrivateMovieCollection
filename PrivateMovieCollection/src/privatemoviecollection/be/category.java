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
public class category {

    private String categoryName;
    private int id;

//Constructor for category object with paramter category_name
    public category(String categoryName) {
        this.categoryName = categoryName;
    }

    public category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public category() {   
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
