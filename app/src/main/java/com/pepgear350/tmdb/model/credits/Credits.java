package com.pepgear350.tmdb.model.credits;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Credits {

    private int id;
    private Cast[] cast;

    public Credits() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cast[] getCast() {
        return cast;
    }

    public void setCast(Cast[] cast) {
        this.cast = cast;
    }
}
