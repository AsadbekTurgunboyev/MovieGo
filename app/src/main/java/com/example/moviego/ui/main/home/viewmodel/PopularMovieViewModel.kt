package com.example.moviego.ui.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviego.domain.models.PopularMoviesModels
import com.example.moviego.domain.usecase.GetMainResponseUseCase
import com.example.moviego.utils.Resource
import com.example.moviego.utils.ResourceState
import kotlinx.coroutines.launch

class PopularMovieViewModel(private val getMainUseCase: GetMainResponseUseCase): ViewModel() {


    private val _popularMovies = MutableLiveData<Resource<PopularMoviesModels>>()
    val popularMovies : LiveData<Resource<PopularMoviesModels>> get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<Resource<PopularMoviesModels>>()
    val topRatedMovies : LiveData<Resource<PopularMoviesModels>> get() = _topRatedMovies

    private val _nowPlayingMovies = MutableLiveData<Resource<PopularMoviesModels>>()
    val nowPlayingMovies: LiveData<Resource<PopularMoviesModels>> get() = _nowPlayingMovies


    fun getPopularMovies(){
        _popularMovies.postValue(Resource(ResourceState.LOADING))
        viewModelScope.launch {
            try {
                val response = getMainUseCase.getPopularMovies()
                _popularMovies.postValue(Resource(ResourceState.SUCCESS,response))
            } catch (e: Exception) {
                _popularMovies.postValue(Resource(ResourceState.ERROR, message = e.message))
                Log.e("xatolik", "getPopularMovies: ${e.message}")
            }
        }
    }

    fun getTopRatedMovies(){
        _topRatedMovies.postValue(Resource(ResourceState.LOADING))
        viewModelScope.launch {
            try {
                val response = getMainUseCase.getTopRatedMovies()
                _topRatedMovies.postValue(Resource(ResourceState.SUCCESS,response))
            }catch (e: Exception){
                Log.e("xatolik", "getPopularMovies: ${e.message}")
                _topRatedMovies.postValue(Resource(ResourceState.ERROR, message = e.message))


            }
        }
    }

    fun getNowPlayingMovies(){
        _nowPlayingMovies.postValue(Resource(ResourceState.LOADING))
        viewModelScope.launch {
            try {
                val response = getMainUseCase.getNowPlayingMovies()
                _nowPlayingMovies.postValue(Resource(ResourceState.SUCCESS,response))
            }catch (e: Exception){
                _nowPlayingMovies.postValue(Resource(ResourceState.ERROR, message = e.message))
            }
        }
    }
}