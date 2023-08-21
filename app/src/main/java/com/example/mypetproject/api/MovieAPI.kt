package com.example.mypetproject.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieAPI {

    @Headers("X-API-KEY: GN83VSH-WVR462G-NXKJ390-89EK5PM")
    @GET("v1.3/movie")
    suspend fun getMovie(
        @Query("selectFields") id: String = "id",
        @Query("selectFields") name: String = "name",
        @Query("selectFields") year: String = "year",
        @Query("selectFields") description: String = "description",
        @Query("selectFields") ratingKp: String = "rating.kp",
        @Query("selectFields") ratingImdb: String = "rating.imdb",
        @Query("selectFields") posterUrl: String = "poster.url",
        @Query("selectFields") videosTrailersUrl: String = "videos.trailers.url",
        @Query("selectFields") movieLength: String = "movieLength",
        @Query("selectFields") ageRating: String = "ageRating",
        @Query("selectFields") genresName: String = "genres.name",
        @Query("selectFields") countriesName: String = "countries.name",
        @Query("selectFields") personsName: String = "persons.name",
        @Query("selectFields") personsPhoto: String = "persons.photo",
        @Query("limit") limit: Int = 99,
        @Query("type") type: String,

    ): Response<MovieResponseData>
}

