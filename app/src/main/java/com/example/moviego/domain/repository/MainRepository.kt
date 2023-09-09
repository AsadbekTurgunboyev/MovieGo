package com.example.moviego.domain.repository

import com.example.moviego.domain.models.DetailMovieModel
import com.example.moviego.domain.models.ImagesMovieModel
import com.example.moviego.domain.models.PopularMoviesModels

interface MainRepository {
    suspend fun getPopularMovies(): PopularMoviesModels

    suspend fun getTopRatedMovies(): PopularMoviesModels

    suspend fun getNowPlayingMovies(): PopularMoviesModels

    suspend fun getDetailMovie(movie_id: Int): DetailMovieModel

    suspend fun getMovieImages(movie_id: Int): ImagesMovieModel
}
