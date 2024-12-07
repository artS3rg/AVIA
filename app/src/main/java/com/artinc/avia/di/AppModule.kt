package com.artinc.avia.di

import com.artinc.network.networks.AirApiService
import com.artinc.network.networks.FeedApiService
import com.artinc.network.networks.TicketsApiService
import com.artinc.network.viewModels.AirViewModel
import com.artinc.network.viewModels.FeedViewModel
import com.artinc.network.viewModels.TicketsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/u/0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // API Service
    single { get<Retrofit>().create(FeedApiService::class.java) }
    single { get<Retrofit>().create(AirApiService::class.java) }
    single { get<Retrofit>().create(TicketsApiService::class.java) }

    // ViewModel
    viewModel { FeedViewModel(get()) }
    viewModel { AirViewModel(get()) }
    viewModel { TicketsViewModel(get()) }
}