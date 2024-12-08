package com.artinc.domain.interfaces

import com.artinc.domain.models.FeedResponse

interface FeedRepository {
    suspend fun getFeedItems(): FeedResponse
}