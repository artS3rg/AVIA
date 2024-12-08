package com.artinc.data.repositories

import com.artinc.data.apis.FeedApiService
import com.artinc.domain.interfaces.FeedRepository
import com.artinc.domain.models.FeedResponse

class FeedRepositoryImpl(private val api: FeedApiService) : FeedRepository {
    override suspend fun getFeedItems() : FeedResponse {
        return api.getFeed()
    }
}