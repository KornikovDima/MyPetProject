package com.example.mypetproject.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypetproject.api.Doc
import com.example.mypetproject.api.MovieAPI
import com.example.mypetproject.model.MovieService
import com.example.mypetproject.model.MoviesListener
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val movieService: MovieService,
    private val movieAPI: MovieAPI
) : ViewModel() {

    private val _movies = MutableLiveData<List<Doc>>()
    val movies: LiveData<List<Doc>> = _movies

    private val listener: MoviesListener = {
        _movies.value = it
    }

    init {
        viewModelScope.launch {
            try {
                val response = movieAPI.getMovie()
                if (response.isSuccessful) {
                    val movieResponseData = response.body()
                    _movies.value = movieResponseData?.docs
                    val liveData: LiveData<List<Doc>> = MutableLiveData(movieResponseData?.docs)
                }
            } catch (e: Exception) {
                    //TODO
            }
        }
    }

    private fun loadMovie() {
        movieService.addListener(listener)
    }

    override fun onCleared() {
        super.onCleared()
        movieService.removeListener(listener)
    }
}