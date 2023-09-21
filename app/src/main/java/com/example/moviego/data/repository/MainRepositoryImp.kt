package com.example.moviego.data.repository

import com.example.moviego.data.source.ApiService
import com.example.moviego.domain.models.DetailMovieModel
import com.example.moviego.domain.models.ImagesMovieModel
import com.example.moviego.domain.models.PlayMovieModel
import com.example.moviego.domain.models.PopularMoviesModels
import com.example.moviego.domain.repository.MainRepository

class MainRepositoryImp(val apiService: ApiService): MainRepository {


    override suspend fun getPopularMovies(): PopularMoviesModels {
        return apiService.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): PopularMoviesModels {
        return apiService.getTopRatedMovies()
    }

    override suspend fun getNowPlayingMovies(): PopularMoviesModels {
        return apiService.getNowPlayingMovies()
    }

    override suspend fun getDetailMovie(movie_id: Int): DetailMovieModel {
        return apiService.getDetailMovie(movie_id = movie_id)
    }

    override suspend fun getMovieImages(movie_id: Int): ImagesMovieModel {
        return apiService.getMovieIMages(movie_id)
    }

    override suspend fun getMoviePlay(movie_id: Int): PlayMovieModel {
        return apiService.getPlayMovie(movie_id)
    }

}