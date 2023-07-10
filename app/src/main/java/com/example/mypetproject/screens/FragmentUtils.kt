package com.example.mypetproject.screens

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mypetproject.App
import com.example.mypetproject.Navigator
import com.example.mypetproject.api.MovieAPI
import com.example.mypetproject.screens.details.MovieDetailsViewModel
import com.example.mypetproject.screens.list.MovieListViewModel

class ViewModelFactory(
    private val app: App
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            MovieListViewModel::class.java -> {
                MovieListViewModel(app.movieService, app.repositoryImpl.getMovie())
            }
            MovieDetailsViewModel::class.java -> {
                MovieDetailsViewModel(app.movieService, app.repositoryImpl.getMovie())
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

fun Fragment.navigator() = requireActivity() as Navigator
