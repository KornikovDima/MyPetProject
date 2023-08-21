package com.example.mypetproject.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mypetproject.R
import com.example.mypetproject.api.Doc
import com.example.mypetproject.databinding.FragmentMovieDetailsBinding
import com.example.mypetproject.screens.navigator


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding: FragmentMovieDetailsBinding get() = _binding!!
    private lateinit var adapter: PersonsAdapter

//    private val viewModel: MovieDetailsViewModel by viewModels { factory() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        adapter = PersonsAdapter()

        val movie = requireArguments().getParcelable<Doc>("movie")

        with(binding) {
            titleMovieTV.text = movie?.name
            descriptionMovieTV.text = movie?.description
            genresValueTV.text = movie?.genres?.joinToString(", ") { genre -> genre.name }
            countriesValueTV.text = movie?.countries?.joinToString(", ") { country -> country.name }
            ratingKpValueTV.text = movie?.rating?.kp.toString()
            ratingImdbValueTV.text = movie?.rating?.imdb.toString()
            yearValueTV.text = movie?.year.toString()
            movieLeghthValueTV.text = movie?.movieLength.toString()
            Glide.with(posterIVDetails)
                .load(movie?.poster?.url)
                .placeholder(R.drawable.image_search)
                .error(R.drawable.image_search)
                .into(posterIVDetails)
            personsRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            personsRV.adapter = adapter

            adapter.persons = movie!!.persons
        }
        binding.pressToBackButton.setOnClickListener { navigator().goBack() }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{

        fun newInstance(movie: Doc) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("movie", movie)
            }
        }
    }
}