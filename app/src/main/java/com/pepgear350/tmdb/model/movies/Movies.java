package com.pepgear350.tmdb.model.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movies {

    private ResultsMovies[] results;

    public Movies() {
    }

    public ResultsMovies[] getResults() {
        return results;
    }

    public void setResults(ResultsMovies[] results) {
        this.results = results;
    }
}
