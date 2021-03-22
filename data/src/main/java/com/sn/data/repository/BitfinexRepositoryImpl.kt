package com.sn.data.repository

import com.sn.data.entity.toSubcribeOrderBookrRequest
import com.sn.data.entity.toSubcribeTickerRequest
import com.sn.data.remote.BitfinexDataSource
import com.sn.domain.entity.SubscribeOrderBook
import com.sn.domain.entity.SubscribeTicker
import com.sn.domain.model.TickerData
import com.sn.domain.model.toOrderBookData
import com.sn.domain.model.toTickerData
import com.sn.domain.repository.BitfinexRepository
import io.reactivex.Flowable
import javax.inject.Inject

class BitfinexRepositoryImpl @Inject constructor(private val bitfinexDataSource: BitfinexDataSource) :
    BitfinexRepository {

    override fun observeTicker(subscribeTicker: SubscribeTicker): Flowable<TickerData> {
        return bitfinexDataSource.subscribeTicker(subscribeTicker.toSubcribeTickerRequest())
            .map { response -> response.toTickerData() }
    }

    override fun observeOrderBook(subscribeOrderBook: SubscribeOrderBook): Flowable<com.sn.domain.model.OrderBookData> {
        return bitfinexDataSource.subscribeOrderBook(subscribeOrderBook.toSubcribeOrderBookrRequest())
            .map { response -> response.toOrderBookData() }
    }
}
