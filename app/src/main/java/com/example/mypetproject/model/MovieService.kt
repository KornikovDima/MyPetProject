package com.example.mypetproject.model

import com.example.mypetproject.MovieNotFoundExceptions
import com.example.mypetproject.api.Doc
import com.example.mypetproject.api.Poster
import com.example.mypetproject.api.Rating
import com.example.mypetproject.api.RepositoryImpl
import com.example.mypetproject.api.Trailer
import com.example.mypetproject.api.Videos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

typealias MoviesListener = (movies: List<Doc>) -> Unit

class MovieService {

    private var movies = listOf<Doc>()

    private val listeners = mutableSetOf<MoviesListener>()

    init {
        movies = (1..100).map {
            Doc(
                description = "Description by movie",
                id = Random.nextInt(),
                name = titles.random(),
                poster = Poster(""),
                rating = Rating(Random.nextDouble(0.0, 1.0), Random.nextDouble(0.0, 1.0)),
                year = Random.nextInt(1968, 2023)

            )
        }
    }

    private fun getMovies(): List<Doc> {
        return movies
    }

    fun getById(id: Int): Doc {
        val movie = movies.firstOrNull { it.id == id } ?: throw MovieNotFoundExceptions()
        return movie
    }

    fun addListener(listener: MoviesListener) {
        listeners.add(listener)
        listener.invoke(getMovies())
    }

    fun removeListener(listener: MoviesListener) {
        listeners.remove(listener)
    }

    companion object {
        val titles = listOf(
            "Матрица",
            "Звездные войны",
            "Игры разума",
            "Крестный отец",
            "Левиафан",
            "Гладиатор",
            "Форет Гамп",
            "Зеленая миля",
            "Инопланетянин",
            "Вики, Кристина, Барселона",
        )
    }
}