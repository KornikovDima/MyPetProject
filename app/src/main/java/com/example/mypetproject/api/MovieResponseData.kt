package com.example.mypetproject.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Random

data class MovieResponseData(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)

@Parcelize
data class Doc(
    val description: String,
    val id: Int,
    val name: String,
    val poster: Poster,
    val rating: Rating,
//    val videos: Videos,
    val year: Int
) : Parcelable

@Parcelize
data class Poster(
    val url: String
): Parcelable

@Parcelize
data class Rating(
    val imdb: Double,
    val kp: Double
):Parcelable

data class Trailer(
    val url: String
)

data class Videos(
    val trailers: List<Trailer>
)

@Parcelize
data class SelectMovie(
    val text: String
): Parcelable