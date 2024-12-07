package com.artinc.network.networks

import com.artinc.core.models.AirResponse
import retrofit2.http.GET

interface AirApiService {
    @GET("uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download")
    suspend fun getAir(): AirResponse
}