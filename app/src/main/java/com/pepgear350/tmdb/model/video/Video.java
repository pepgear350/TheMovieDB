package com.pepgear350.tmdb.model.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {

    private ResultsVideo[] results;

    public Video() {
    }

    public ResultsVideo[] getResults() {
        return results;
    }

    public void setResults(ResultsVideo[] results) {
        this.results = results;
    }
}
