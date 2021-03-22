package com.sn.socketbitfinex_rxjava.di

import com.sn.data.repository.BitfinexRepositoryImpl
import com.sn.domain.usecase.ObserveOrderBookUseCase
import com.sn.domain.usecase.ObserveTickerUseCase
import com.sn.socketbitfinex_rxjava.ui.MainViewModel
import com.sn.socketbitfinex_rxjava.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideMainViewModel(useCases: UseCases) =
        MainViewModel(useCases)

    @Provides
    fun provideUseCases(bitfinexRepositoryImpl: BitfinexRepositoryImpl) = UseCases(
        ObserveOrderBookUseCase(bitfinexRepositoryImpl),
        ObserveTickerUseCase(bitfinexRepositoryImpl)
    )

 /*   @Singleton
    @Provides
    fun provideScarletRepository(bitfinexApi: BitfinexApi) =
        ScarletRepository(bitfinexApi)*/
}