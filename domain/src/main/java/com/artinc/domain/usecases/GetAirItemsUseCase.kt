package com.artinc.domain.usecases

import com.artinc.domain.interfaces.AirRepository

class GetAirItemsUseCase(private val repository: AirRepository) {
    suspend operator fun invoke() = repository.getAirItems().tickets_offers
}
