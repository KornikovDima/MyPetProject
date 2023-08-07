package com.example.mypetproject.screens.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypetproject.api.Doc
import com.example.mypetproject.api.RepositoryImpl
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {

    private val _movies = MutableLiveData<List<Doc>>()
    val movies: LiveData<List<Doc>> = _movies

    fun loadMovieNetwork(param: String) = viewModelScope.launch {
        repositoryImpl.getMovie().getMovie(type = param).let { response ->
            if (response.isSuccessful) {
                _movies.postValue(response.body()?.docs)
            } else {
                Log.d("tag", "response error: ${response.code()}")
            }
        }
    }
}


