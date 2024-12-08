package com.artinc.data

import com.artinc.data.apis.AirApiService
import com.artinc.data.apis.FeedApiService
import com.artinc.data.apis.TicketsApiService
import com.artinc.data.repositories.AirRepositoryImpl
import com.artinc.data.repositories.FeedRepositoryImpl
import com.artinc.data.repositories.TicketsRepositoryImpl
import com.artinc.domain.interfaces.AirRepository
import com.artinc.domain.interfaces.FeedRepository
import com.artinc.domain.interfaces.TicketsRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single { createRetrofit() }
    single { get<Retrofit>().create(AirApiService::class.java) }
    single { get<Retrofit>().create(FeedApiService::class.java) }
    single { get<Retrofit>().create(TicketsApiService::class.java) }
    single<AirRepository> { AirRepositoryImpl(get()) }
    single<FeedRepository> { FeedRepositoryImpl(get()) }
    single<TicketsRepository> { TicketsRepositoryImpl(get()) }
}

fun createRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://drive.usercontent.google.com/u/0/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()