package com.artinc.domain.models

import com.artinc.domain.models.FeedItem

data class FeedResponse (
    val offers: List<FeedItem>
)