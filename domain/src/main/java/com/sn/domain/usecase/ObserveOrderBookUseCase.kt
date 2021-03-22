package com.sn.domain.usecase

import com.sn.domain.entity.BaseSubscribe
import com.sn.domain.entity.SubscribeOrderBook
import com.sn.domain.model.OrderBookData
import com.sn.domain.repository.BitfinexRepository
import com.sn.domain.util.UseCase
import io.reactivex.Flowable

class ObserveOrderBookUseCase(private val bitfinexRepository: BitfinexRepository) :
    UseCase.FlowableUseCase<UseCase.None, OrderBookData> {
    override fun execute(params: UseCase.None): Flowable<OrderBookData> {
        val subscribeOrderBook = SubscribeOrderBook(
            event = BaseSubscribe.SUBSCRIBE_EVENT,
            channel = BaseSubscribe.ORDER_BOOK_CHANNEL,
            pair = BaseSubscribe.BTCUSD_PAIR,
            frequency = BaseSubscribe.FREQUENCY_ZERO
        )
        return bitfinexRepository.observeOrderBook(subscribeOrderBook)
    }
}