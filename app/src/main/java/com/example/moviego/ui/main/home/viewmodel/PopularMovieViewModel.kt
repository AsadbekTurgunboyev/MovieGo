package com.example.moviego.ui.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviego.domain.models.PopularMoviesModels
import com.example.moviego.domain.usecase.GetMainResponseUseCase
import kotlinx.coroutines.launch

class PopularMovieViewModel(private val getMainUseCase: GetMainResponseUseCase): ViewModel() {


    private val _popularMovies = MutableLiveData<PopularMoviesModels>()
    val popularMovies : LiveData<PopularMoviesModels> get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<PopularMoviesModels>()
    val topRatedMovies : LiveData<PopularMoviesModels> get() = _topRatedMovies


    fun getPopularMovies(){
        viewModelScope.launch {
            try {
                val response = getMainUseCase.getPopularMovies()
                _popularMovies.postValue(response)
            } catch (e: Exception) {
                Log.e("xatolik", "getPopularMovies: ${e.message}")
            }
        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch {
            try {
                val response = getMainUseCase.getTopRatedMovies()
                _topRatedMovies.postValue(response)
            }catch (e: Exception){
                Log.e("xatolik", "getPopularMovies: ${e.message}")

            }
        }
    }
}