package com.sn.socketbitfinex_rxjava

import com.sn.domain.usecase.ObserveOrderBookUseCase
import com.sn.domain.usecase.ObserveTickerUseCase

data class UseCases(
    val observeOrderBookUseCase: ObserveOrderBookUseCase,
    val observeTickerUseCase: ObserveTickerUseCase
)
