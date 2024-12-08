package com.artinc.data.apis

import com.artinc.domain.models.AirResponse
import retrofit2.http.GET

interface AirApiService {
    @GET("uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download")
    suspend fun getAir(): AirResponse
}