package com.artinc.core.models

import com.artinc.core.models.FeedItem

data class FeedResponse (
    val offers: List<FeedItem>
)