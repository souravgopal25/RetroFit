package com.retorFit;

import com.retorFit.Model.MovieResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
//https://api.themoviedb.org/3/movie/popular?api_key=c116a273a05858824b03df6c4cfcbf20&language=en-US&page=1
public interface ApiInterface {
    @GET("/3/movie/{category}")
    Call<MovieResults>listofMovies(@Path("category") String category,
                                @Query("api_key") String APIKEY,
                                @Query("language") String language,
                                @Query("page") int page
    );
}
