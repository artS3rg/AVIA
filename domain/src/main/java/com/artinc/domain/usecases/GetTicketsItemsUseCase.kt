package com.artinc.domain.usecases

import com.artinc.domain.interfaces.TicketsRepository

class GetTicketsItemsUseCase(private val repository: TicketsRepository) {
    suspend operator fun invoke() = repository.getTicketsItems().tickets
}
