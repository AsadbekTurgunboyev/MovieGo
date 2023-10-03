package com.example.moviego.ui.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviego.domain.models.SearchMovieModel
import com.example.moviego.domain.models.SearchMovieWithKeywordModel
import com.example.moviego.domain.usecase.GetMainResponseUseCase
import com.example.moviego.utils.Resource
import com.example.moviego.utils.ResourceState
import kotlinx.coroutines.launch

class SearchViewModel(val getMainResponseUseCase: GetMainResponseUseCase) : ViewModel() {


    val _searchMovie = MutableLiveData<Resource<SearchMovieModel>>()
    val searchMovie: LiveData<Resource<SearchMovieModel>> get() =  _searchMovie

    val _searchMovieWithKeyword = MutableLiveData<Resource<SearchMovieWithKeywordModel>>()
    val searchMovieWithKeyword : LiveData<Resource<SearchMovieWithKeywordModel>> get() =  _searchMovieWithKeyword

    fun getSearchMovie(query: String) {
        _searchMovie.postValue(Resource(ResourceState.LOADING))
        viewModelScope.launch {
            try {
                val response = getMainResponseUseCase.searchMovie(query = query)
                _searchMovie.postValue(Resource(ResourceState.SUCCESS, response))
            } catch (e: Exception) {
                _searchMovie.postValue(Resource(ResourceState.ERROR, message = e.message))

            }
        }
    }

    fun getSearchMovieWithKeyword(query: String) {
        _searchMovieWithKeyword.postValue(Resource(ResourceState.LOADING))
        viewModelScope.launch {
            try {
                val response = getMainResponseUseCase.searchMovieWithKeyword(query = query)
                _searchMovieWithKeyword.postValue(Resource(ResourceState.SUCCESS, response))
            } catch (e: Exception) {
                _searchMovieWithKeyword.postValue(Resource(ResourceState.ERROR, message = e.message))

            }
        }
    }
}