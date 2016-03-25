package com.udacity.akshay.redditoria.Data;

/**
 * Created by A on 3/18/2016.
 */
public class Posts {
    private String subreddit;
    private String title;
    private String author;
    private int points;
    private int numComments;
    private String permalink;
    private String url;
    private String domain;
    private String id;

    public String getDetails(){
        String details=author
                +" posted this and got "
                +numComments
                +" replies";
        return details;
    }
    public void setTitle(String t){
        title=t;
    }
    public void setSubReddit(String s){
        subreddit=s;
    }
    public void setAuthor(String a){
        author=a;
    }
    public void setPoints(int p){
        points=p;
    }
    public void setNumComments(int n){
        numComments=n;
    }
    public void setPermalink(String p){
        permalink=p;
    }
    public void setUrl(String u){
        url=u;
    }
    public void setDomain(String d){
        domain=d;
    }
    public void setID(String i){
        id=i;
    }
    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }
    public String getPermalink(){
        return permalink;
    }
    public String getUrl(){
        return url;
    }
    public int getPoints(){
        return points;
    }
    public int numComments(){
        return numComments;
    }
    public String getScore(){
        return Integer.toString(points);
    }
}
