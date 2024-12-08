package com.artinc.data.repositories

import com.artinc.data.apis.TicketsApiService
import com.artinc.domain.interfaces.TicketsRepository
import com.artinc.domain.models.TicketsResponse

class TicketsRepositoryImpl(private val api: TicketsApiService) : TicketsRepository {
    override suspend fun getTicketsItems() : TicketsResponse {
        return api.getTickets()
    }
}