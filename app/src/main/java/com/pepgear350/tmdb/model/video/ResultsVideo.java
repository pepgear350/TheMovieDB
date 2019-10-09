package com.pepgear350.tmdb.model.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true)
public class ResultsVideo {

    private String id;
    private String key;


    public ResultsVideo() {
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        String path = "https://www.youtube.com/embed/" + key;
        this.key = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}