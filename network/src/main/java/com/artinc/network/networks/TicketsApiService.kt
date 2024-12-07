package com.artinc.network.networks

import com.artinc.core.models.TicketsResponse
import retrofit2.http.GET

interface TicketsApiService {
    @GET("uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getTickets(): TicketsResponse
}