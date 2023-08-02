package sg.edu.rp.c346.movielist;

import java.io.Serializable;

public class Movie implements Serializable {

    private int year;
    private String genre;
    private int id;
    private String title;
    private String rating;
    public Movie(int id,String title,String genre, int year, String rating){
        this.id=id;
        this.genre=genre;
        this.title=title;
        this.year=year;
        this.rating =rating;
    }
    public int getId(){return id;}
    public int getYear(){
        return year;
    }
    public String getrating(){
        return rating;
    }
    public String getTitle(){
        return title;
    }
    public String getgenre(){return genre;}

    public void setTitle(String x){
        title=x;
    }
    public void setYear(int x ){
        year=x;
    }
    public void setGenre(String x ){
        genre=x;
    }
    public void setRating(String x){
        rating=x;
    }

}
