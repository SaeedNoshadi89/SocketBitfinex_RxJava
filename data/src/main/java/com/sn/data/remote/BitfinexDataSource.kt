package com.sn.data.remote

import com.sn.data.entity.SubscribeOrderBookRequest
import com.sn.data.entity.SubscribeTickerRequest
import io.reactivex.Flowable

interface BitfinexDataSource {

    fun subscribeTicker(subscribeTickerRequest: SubscribeTickerRequest): Flowable<Array<String>>

    fun subscribeOrderBook(subscribeOrderBookRequest: SubscribeOrderBookRequest): Flowable<DoubleArray>
}
