package com.example.mypetproject.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypetproject.MovieNotFoundExceptions
import com.example.mypetproject.api.Doc
import com.example.mypetproject.api.MovieAPI
import com.example.mypetproject.model.MovieService

class MovieDetailsViewModel(
    private val movieService: MovieService,
    private val movieAPI: MovieAPI
) : ViewModel() {

//    private val _movieDetails = MutableLiveData<Movie>()
//    val movieDetails: LiveData<Movie> = _movieDetails
    private val _movieDetails = MutableLiveData<Doc>()
    val movieDetails: LiveData<Doc> = _movieDetails

    fun loadMovie(movieId: Int) {
        if (_movieDetails.value != null) return
        try {
            _movieDetails.value = movieService.getById(movieId)
        } catch (e: MovieNotFoundExceptions) {
            e.printStackTrace()
        }
    }
}