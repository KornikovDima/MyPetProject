package com.example.mypetproject.api

import retrofit2.Call
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
        @Query("limit") limit: Int = 99,
        @Query("type") type: String,

    ): Response<MovieResponseData>
}

