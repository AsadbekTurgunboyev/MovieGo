package com.example.moviego.data.repository

import com.example.moviego.data.source.ApiService
import com.example.moviego.domain.models.PopularMoviesModels
import com.example.moviego.domain.repository.MainRepository

class MainRepositoryImp(val apiService: ApiService): MainRepository {


    override suspend fun getPopularMovies(): PopularMoviesModels {
        return apiService.getPopularMovies()
    }
}