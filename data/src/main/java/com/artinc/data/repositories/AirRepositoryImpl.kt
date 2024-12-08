package com.artinc.data.repositories

import com.artinc.data.apis.AirApiService
import com.artinc.domain.interfaces.AirRepository
import com.artinc.domain.models.AirResponse

class AirRepositoryImpl(private val api: AirApiService) : AirRepository {
    override suspend fun getAirItems(): AirResponse {
        return api.getAir()
    }
}