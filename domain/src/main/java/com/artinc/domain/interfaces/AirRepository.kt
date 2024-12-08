package com.artinc.domain.interfaces

import com.artinc.domain.models.AirResponse

interface AirRepository {
    suspend fun getAirItems(): AirResponse
}