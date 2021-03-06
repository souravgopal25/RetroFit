package com.retorFit;

import com.retorFit.Model.MovieResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/3/movie/{category}")
    Call<MovieResults>listofMovies(@Path("category") String category,
                                @Query("api_key") String APIKEY,
                                @Query("language") String language,
                                @Query("page") int page
    );
}
