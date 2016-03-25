package com.udacity.akshay.redditoria.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 3/18/2016.
 */
public class PostsHolder {
    
    private final String URL_TEMPLATE= "http://www.reddit.com/r/SUBREDDIT_NAME/" +".json" +"?after=AFTER";

    String subreddit;
    String url;
    String after;

    public PostsHolder(String sr){
        subreddit=sr;
        after="";
        generateURL();
    }
    
    private void generateURL(){
        url=URL_TEMPLATE.replace("SUBREDDIT_NAME", subreddit);
        url=url.replace("AFTER", after);
    }
    public List<Posts> fetchPosts(){
        String raw=Data.readContents(url);
        List<Posts> list=new ArrayList<Posts>();
        try{
            JSONObject data=new JSONObject(raw)
                    .getJSONObject("data");
            JSONArray children=data.getJSONArray("children");

            //Using this property we can fetch the next set of
            //posts from the same subreddit
            after=data.getString("after");

            for(int i=0;i<children.length();i++){
                JSONObject jason=children.getJSONObject(i)
                        .getJSONObject("data");
                Posts p=new Posts();
                p.setTitle(jason.optString("title"));
                p.setUrl(jason.optString("url"));
                p.setNumComments(jason.optInt("num_comments"));
                p.setPoints(jason.optInt("score"));
                p.setAuthor(jason.optString("author"));
                p.setSubReddit(jason.optString("subreddit"));
                p.setPermalink(jason.optString("permalink"));
                p.setDomain(jason.optString("domain"));
                p.setID(jason.optString("id"));
                if(p.getTitle()!=null)
                    list.add(p);
            }
        }catch(Exception e){
            Log.e("fetchPosts()", e.toString());
        }
        return list;
    }

    public List<Posts> fetchMorePosts(){
        generateURL();
        return fetchPosts();
    }
}
