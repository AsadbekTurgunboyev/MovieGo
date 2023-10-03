package com.example.moviego.data.source

import com.example.moviego.domain.models.DetailMovieModel
import com.example.moviego.domain.models.ImagesMovieModel
import com.example.moviego.domain.models.PlayMovieModel
import com.example.moviego.domain.models.PopularMoviesModels
import com.example.moviego.domain.models.SearchMovieModel
import com.example.moviego.domain.models.SearchMovieWithKeywordModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMoviesModels

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): PopularMoviesModels

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): PopularMoviesModels

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movie_id: Int): DetailMovieModel

    @GET("movie/{movie_id}/images")
    suspend fun getMovieIMages(@Path("movie_id") movie_id: Int): ImagesMovieModel

    @GET("movie/{movie_id}/videos")
    suspend fun getPlayMovie(@Path("movie_id") movie_id: Int): PlayMovieModel

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String): SearchMovieModel

    @GET("search/keyword")
    suspend fun searchMovieWithKeyword(@Query("query") query: String): SearchMovieWithKeywordModel


}