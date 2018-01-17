package com.carlesarnal.themoviedb.api;

/**
 * Created by carles on 17/01/18.
 */

import com.carlesarnal.themoviedb.api.model.Movies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {



    @GET("/3/movie/top_rated")
    Call<Movies> getTopRatedMovies(@Query("page") int page);

    @GET("/3/movie/{movie_id}/similar")
    Call<Movies> getRelatedMovies(@Path("id") int id);


}
