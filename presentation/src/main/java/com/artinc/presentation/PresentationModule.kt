package com.artinc.presentation

import com.artinc.presentation.viewModels.AirViewModel
import com.artinc.presentation.viewModels.FeedViewModel
import com.artinc.presentation.viewModels.TicketsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    // ViewModel
    viewModel { FeedViewModel(get()) }
    viewModel { AirViewModel(get()) }
    viewModel { TicketsViewModel(get()) }
}