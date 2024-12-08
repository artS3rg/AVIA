package com.artinc.domain.interfaces

import com.artinc.domain.models.TicketsResponse

interface TicketsRepository {
    suspend fun getTicketsItems(): TicketsResponse
}