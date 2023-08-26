package com.example.moviego.domain.usecase

import com.example.moviego.domain.repository.MainRepository

class GetMainResponseUseCase(private val mainRepository: MainRepository) {

    suspend fun getPopularMovies() = mainRepository.getPopularMovies()

    suspend fun getTopRatedMovies() = mainRepository.getTopRatedMovies()
}