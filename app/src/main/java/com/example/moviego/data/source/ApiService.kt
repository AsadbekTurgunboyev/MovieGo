package com.example.moviego.data.source

import com.example.moviego.domain.models.PopularMoviesModels
import retrofit2.http.GET

interface ApiService {


    @GET("popular")
    suspend fun getPopularMovies(): PopularMoviesModels
}