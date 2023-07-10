package com.example.mypetproject

import android.app.Application
import com.example.mypetproject.api.MovieAPI
import com.example.mypetproject.api.RepositoryImpl
import com.example.mypetproject.model.MovieService

class App : Application() {
    val movieService = MovieService()
    val repositoryImpl = RepositoryImpl()
}