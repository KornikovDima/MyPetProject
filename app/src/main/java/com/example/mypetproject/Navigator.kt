package com.example.mypetproject

import androidx.fragment.app.Fragment
import com.example.mypetproject.api.Doc

interface Navigator {

    fun startFragment(fragment: Fragment)

    fun showDetails(movie: Doc)

    fun goBack()

    fun toast(messageRes: Int)
}