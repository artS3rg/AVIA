package com.artinc.data.apis

import com.artinc.domain.models.TicketsResponse
import retrofit2.http.GET

interface TicketsApiService {
    @GET("uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getTickets(): com.artinc.domain.models.TicketsResponse
}