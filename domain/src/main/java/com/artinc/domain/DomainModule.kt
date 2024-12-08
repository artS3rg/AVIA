package com.artinc.domain

import com.artinc.domain.usecases.GetAirItemsUseCase
import com.artinc.domain.usecases.GetFeedItemsUseCase
import com.artinc.domain.usecases.GetTicketsItemsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetFeedItemsUseCase(get()) }
    factory { GetAirItemsUseCase(get()) }
    factory { GetTicketsItemsUseCase(get()) }
}