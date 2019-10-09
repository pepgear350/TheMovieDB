package com.pepgear350.tmdb.api;

import com.pepgear350.tmdb.model.credits.Credits;
import com.pepgear350.tmdb.model.movies.Movies;
import com.pepgear350.tmdb.model.movies.ResultsMovies;
import com.pepgear350.tmdb.model.video.Video;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {

    String AUTHORIZATION = "Authorization";
    String QUERY = "query";

    @GET("movie/popular")
    Observable<Movies> getPopular(
            @Header(AUTHORIZATION) String authHeader);



    @GET("movie/top_rated")
    Observable<Movies> getTopRated(
            @Header(AUTHORIZATION) String authHeader);


    @GET("movie/upcoming")
    Observable<Movies> getUpcoming(
            @Header(AUTHORIZATION) String authHeader);


    @GET("search/movie")
    Observable<Movies> searchMovies(
            @Header(AUTHORIZATION) String authHeader,
            @Query(QUERY) String query);


    @GET("movie/{movie_id}/credits")
    Observable<Credits> searchCredits(
            @Header(AUTHORIZATION) String authHeader,
            @Path("movie_id") int movie_id);


    @GET("movie/{movie_id}/videos")
    Observable<Video> searchVideos(
            @Header(AUTHORIZATION) String authHeader,
            @Path("movie_id") int movie_id);

    @GET("movie/{movie_id}")
    Observable<ResultsMovies> getMovieDetails(
            @Header(AUTHORIZATION) String authHeader,
            @Path("movie_id") int movie_id);
}
