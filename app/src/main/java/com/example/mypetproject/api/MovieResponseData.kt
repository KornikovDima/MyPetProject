package com.example.mypetproject.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MovieResponseData(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)

@Parcelize
data class Doc(
    val ageRating: Int,
    val countries: List<Country>,
    val description: String,
    val genres: List<Genre>,
    val id: Int,
    val name: String,
    val movieLength: Int,
    val persons: List<Person>,
    val poster: Poster,
    val rating: Rating,
    val videos: Videos,
    val year: Int
) : Parcelable

@Parcelize
data class Country(
    val name: String
): Parcelable

@Parcelize
data class Genre(
    val name: String
): Parcelable

@Parcelize
data class Person(
    val name: String,
    val photo: String
): Parcelable

@Parcelize
data class Poster(
    val url: String
): Parcelable

@Parcelize
data class Rating(
    val imdb: Double,
    val kp: Double
):Parcelable

@Parcelize
data class Trailer(
    val url: String
):Parcelable

@Parcelize
data class Videos(
    val trailers: List<Trailer>
):Parcelable
