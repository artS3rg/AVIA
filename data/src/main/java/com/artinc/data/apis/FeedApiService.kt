package com.artinc.data.apis

import com.artinc.domain.models.FeedResponse
import retrofit2.http.GET

interface FeedApiService {
    @GET("uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download")
    suspend fun getFeed(): com.artinc.domain.models.FeedResponse
}