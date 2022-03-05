package com.example.travelplanner.data.remote


import android.content.res.Resources
import com.example.travelplanner.R
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = Resources.getSystem().getString(R.string.IMAGE_API)
    ): Response<ImageResponse>
}