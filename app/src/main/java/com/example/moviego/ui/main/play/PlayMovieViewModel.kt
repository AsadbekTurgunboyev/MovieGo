package com.example.moviego.ui.main.play

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviego.domain.models.PlayMovieModel
import com.example.moviego.domain.models.PopularMoviesModels
import com.example.moviego.domain.usecase.GetMainResponseUseCase
import com.example.moviego.utils.Resource
import com.example.moviego.utils.ResourceState
import kotlinx.coroutines.launch

class PlayMovieViewModel(private val getMainUseCase: GetMainResponseUseCase): ViewModel() {

    private val _playMovies = MutableLiveData<Resource<PlayMovieModel>>()
    val playMovies : LiveData<Resource<PlayMovieModel>> get() = _playMovies


    fun getPlayMovie(movie_id: Int){
        _playMovies.postValue(Resource(ResourceState.LOADING))

        viewModelScope.launch {
            try {
                val response = getMainUseCase.getMoviePlay(movie_id)
                _playMovies.postValue(Resource(ResourceState.SUCCESS,response))
            } catch (e: Exception) {
                _playMovies.postValue(Resource(ResourceState.ERROR, message = e.message))
                Log.e("xatolik", "getPopularMovies: ${e.message}")
            }
        }

    }


}