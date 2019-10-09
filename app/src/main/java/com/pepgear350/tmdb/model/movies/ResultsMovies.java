package com.pepgear350.tmdb.model.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( ignoreUnknown = true)
public class ResultsMovies {

    private int id;
    private String backdrop_path;
    private String title;
    private int vote_average;
    private String overview;
    private String release_date;
    private String poster_path;

    public ResultsMovies() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        String path = "https://image.tmdb.org/t/p/w300" + backdrop_path;
        this.backdrop_path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        String path = "https://image.tmdb.org/t/p/w185" + poster_path;
        this.poster_path = path;
    }
}