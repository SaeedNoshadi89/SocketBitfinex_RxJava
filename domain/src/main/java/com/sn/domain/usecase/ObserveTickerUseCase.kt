package com.sn.domain.usecase

import com.sn.domain.entity.BaseSubscribe
import com.sn.domain.entity.SubscribeTicker
import com.sn.domain.model.TickerData
import com.sn.domain.repository.BitfinexRepository
import com.sn.domain.util.UseCase
import io.reactivex.Flowable

class ObserveTickerUseCase(private val bitfinexRepository: BitfinexRepository): UseCase.FlowableUseCase<UseCase.None, TickerData> {
    override fun execute(params: UseCase.None): Flowable<TickerData> {
        val subscribeTicker = SubscribeTicker(
            event = BaseSubscribe.SUBSCRIBE_EVENT,
            channel = BaseSubscribe.TICKER_CHANNEL,
            pair = BaseSubscribe.BTCUSD_PAIR
        )
        return bitfinexRepository.observeTicker(subscribeTicker)
    }

}