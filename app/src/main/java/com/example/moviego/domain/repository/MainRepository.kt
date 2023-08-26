package com.example.moviego.domain.repository

import com.example.moviego.domain.models.PopularMoviesModels

interface MainRepository {
    suspend fun getPopularMovies(): PopularMoviesModels
}