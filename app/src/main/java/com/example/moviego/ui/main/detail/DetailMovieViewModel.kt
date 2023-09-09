package com.example.moviego.ui.main.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviego.domain.models.DetailMovieModel
import com.example.moviego.domain.models.ImagesMovieModel
import com.example.moviego.domain.usecase.GetMainResponseUseCase
import com.example.moviego.utils.Resource
import com.example.moviego.utils.ResourceState
import kotlinx.coroutines.launch

class DetailMovieViewModel(val getMainResponseUseCase: GetMainResponseUseCase) : ViewModel(){

    private val _getDetailMovie = MutableLiveData<Resource<DetailMovieModel>>()
    val detailMovie : LiveData<Resource<DetailMovieModel>> get() = _getDetailMovie

    private val _imagesMovie = MutableLiveData<Resource<ImagesMovieModel>>()
    val imagesMovie : LiveData<Resource<ImagesMovieModel>> get() = _imagesMovie



    fun getDetailMovie(movie_id: Int){
        _getDetailMovie.postValue(Resource(ResourceState.LOADING))
        viewModelScope.launch {
            try {
                val response = getMainResponseUseCase.getDetailMovie(movie_id = movie_id)
                _getDetailMovie.postValue(Resource(ResourceState.SUCCESS,response))
            }catch (e: Exception){

                _getDetailMovie.postValue(Resource(ResourceState.ERROR, message = e.message))


            }
        }

    }

    fun getMovieImages(movie_id: Int){
        _imagesMovie.postValue(Resource(ResourceState.LOADING))
        viewModelScope.launch {
            try {
                val response = getMainResponseUseCase.getMovieImages(movie_id = movie_id)
                _imagesMovie.postValue(Resource(ResourceState.SUCCESS,response))
            }catch (e: Exception){

                _imagesMovie.postValue(Resource(ResourceState.ERROR, message = e.message))


            }
        }
    }

}