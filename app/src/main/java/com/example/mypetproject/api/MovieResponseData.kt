package com.example.mypetproject.api

data class MovieResponseData(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)

data class Doc(
    val description: String,
    val id: Int,
    val name: String,
    val poster: Poster,
    val rating: Rating,
//    val videos: Videos,
    val year: Int
)

data class Poster(
    val url: String
)

data class Rating(
    val imdb: Double,
    val kp: Double
)

data class Trailer(
    val url: String
)

data class Videos(
    val trailers: List<Trailer>
)