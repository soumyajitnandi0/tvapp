package com.example.tvapp.data.api

import com.example.tvapp.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PexelsApi {
    @GET("videos/popular")
    suspend fun getPopularVideos(
        @Header("Authorization") authorization: String,
        @Query("per_page") perPage: Int = 20
    ): VideoResponse

    @GET("videos/search")
    suspend fun searchVideos(
        @Header("Authorization") authorization: String,
        @Query("query") query: String,
        @Query("per_page") perPage: Int = 20
    ): VideoResponse
}