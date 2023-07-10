package com.example.mypetproject.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypetproject.api.Doc
import com.example.mypetproject.databinding.FragmentMovieListBinding
import com.example.mypetproject.screens.factory
import com.example.mypetproject.screens.navigator

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding get() = _binding!!
    private lateinit var adapter: MovieAdapter
    private val viewModel: MovieListViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)

        adapter = MovieAdapter(object : MovieActionListener {
            override fun onMovieDetails(movie: Doc) { navigator().showDetails(movie) }})

        viewModel.movies.observe(viewLifecycleOwner, Observer { adapter.movies = it })

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}