package com.example.moviego.domain.repository

import com.example.moviego.domain.models.DetailMovieModel
import com.example.moviego.domain.models.ImagesMovieModel
import com.example.moviego.domain.models.PlayMovieModel
import com.example.moviego.domain.models.PopularMoviesModels
import com.example.moviego.domain.models.SearchMovieModel
import com.example.moviego.domain.models.SearchMovieWithKeywordModel

interface MainRepository {
    suspend fun getPopularMovies(): PopularMoviesModels

    suspend fun getTopRatedMovies(): PopularMoviesModels

    suspend fun getNowPlayingMovies(): PopularMoviesModels

    suspend fun getDetailMovie(movie_id: Int): DetailMovieModel

    suspend fun getMovieImages(movie_id: Int): ImagesMovieModel

    suspend fun getMoviePlay(movie_id: Int): PlayMovieModel

    suspend fun searchMovie(query:String): SearchMovieModel

    suspend fun searchMovieWithKeyword(query:String): SearchMovieWithKeywordModel
}
