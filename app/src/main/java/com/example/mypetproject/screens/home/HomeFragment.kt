package com.example.mypetproject.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.mypetproject.databinding.FragmentHomeBinding
import com.example.mypetproject.screens.list.MovieListFragment
import com.example.mypetproject.screens.navigator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.movie.setOnClickListener { navigator().startFragment(fragmentWithBundle("movie")) }
        binding.serials.setOnClickListener { navigator().startFragment(fragmentWithBundle("tv-series")) }
        binding.cartoon.setOnClickListener { navigator().startFragment((fragmentWithBundle("cartoon"))) }
        binding.anime.setOnClickListener { navigator().startFragment((fragmentWithBundle("anime"))) }
        binding.animeSeries.setOnClickListener { navigator().startFragment((fragmentWithBundle("animated-series"))) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fragmentWithBundle(type: String): Fragment {
        val bundle = bundleOf("param" to type)
        val movieListFragment = MovieListFragment()
        movieListFragment.arguments = bundle
        return movieListFragment
    }
}

