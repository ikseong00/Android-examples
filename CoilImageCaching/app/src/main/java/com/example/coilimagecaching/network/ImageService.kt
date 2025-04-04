package com.example.coilimagecaching.network

import com.example.coilimagecaching.model.ImageUrlResponse
import retrofit2.http.GET

interface ImageService {
    @GET("/api/cache-data")
    suspend fun getImages(): ImageUrlResponse
}