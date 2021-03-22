package com.sn.data.remote

import com.sn.data.entity.JsonResponse
import com.sn.data.entity.SubscribeOrderBookRequest
import com.sn.data.entity.SubscribeTickerRequest
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface BitfinexApi {

    @Receive
    fun openWebSocketEvent(): Flowable<WebSocket.Event>

    @Receive
    fun receiveResponse(): Flowable<JsonResponse>

    @Send
    fun sendTickerRequest(subscribeTickerRequest: SubscribeTickerRequest)

    @Receive
    fun observeTicker(): Flowable<Array<String>>

    @Send
    fun sendOrderBookRequest(subscribeOrderBookRequest: SubscribeOrderBookRequest)

    @Receive
    fun observeOrderBook(): Flowable<DoubleArray>

}