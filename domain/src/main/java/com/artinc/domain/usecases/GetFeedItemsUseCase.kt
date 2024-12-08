package com.artinc.domain.usecases

import com.artinc.domain.interfaces.FeedRepository

class GetFeedItemsUseCase(private val repository: FeedRepository) {
    suspend operator fun invoke() = repository.getFeedItems().offers
}
