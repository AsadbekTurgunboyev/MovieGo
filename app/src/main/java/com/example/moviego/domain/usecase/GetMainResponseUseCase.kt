package com.example.moviego.domain.usecase

import com.example.moviego.domain.repository.MainRepository

class GetMainResponseUseCase(private val mainRepository: MainRepository) {

    suspend fun getPopularMovies() = mainRepository.getPopularMovies()

    suspend fun getTopRatedMovies() = mainRepository.getTopRatedMovies()

    suspend fun getNowPlayingMovies() = mainRepository.getNowPlayingMovies()

    suspend fun getDetailMovie(movie_id: Int) = mainRepository.getDetailMovie(movie_id = movie_id)

    suspend fun getMovieImages(movie_id: Int) = mainRepository.getMovieImages(movie_id = movie_id)

    suspend fun getMoviePlay(movie_id: Int) = mainRepository.getMoviePlay(movie_id)
}