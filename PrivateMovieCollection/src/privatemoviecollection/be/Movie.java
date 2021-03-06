/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.be;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author pmj
 */
public class Movie {

    private int id;
    private String movieName;
    double imdbRating;
    double privateRating;
    String fileLink;
    long lastView;

    public Movie(int id, String movieName, double imdbRating, double privateRating, String fileLink, long lastView) {
        this.id = id;
        this.movieName = movieName;
        this.imdbRating = imdbRating;
        this.privateRating = privateRating;
        this.fileLink = fileLink;
        this.lastView = lastView;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public double getPrivateRating() {
        return privateRating;
    }

    public void setPrivateRating(double privateRating) {
        this.privateRating = privateRating;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public long getLastView() {
        return lastView;
    }

    public void setLastView(Long lastView) {
        this.lastView = lastView;
    }
    
    public String getDateLastviewed(){
        Date date = new Date(lastView);
        String s = DateFormat.getDateInstance().format(date);
        return s;
    }

}
