package com.sn.domain.repository

import com.sn.domain.entity.SubscribeOrderBook
import com.sn.domain.entity.SubscribeTicker
import com.sn.domain.model.OrderBookData
import com.sn.domain.model.TickerData
import io.reactivex.Flowable


interface BitfinexRepository {

    fun observeTicker(subscribeTicker: SubscribeTicker): Flowable<TickerData>

    fun observeOrderBook(subscribeOrderBook: SubscribeOrderBook): Flowable<OrderBookData>
}