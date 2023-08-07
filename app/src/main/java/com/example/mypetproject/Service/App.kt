package com.example.mypetproject.Service

import android.app.Application
import com.example.mypetproject.api.RepositoryImpl

class App : Application() {
    val repositoryImpl = RepositoryImpl()
}