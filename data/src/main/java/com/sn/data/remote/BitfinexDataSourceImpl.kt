package com.sn.data.remote

import android.annotation.SuppressLint
import com.sn.data.entity.SubscribeOrderBookRequest
import com.sn.data.entity.SubscribeTickerRequest
import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("CheckResult")
class BitfinexDataSourceImpl @Inject constructor(private val bitfinexApi: BitfinexApi) : BitfinexDataSource {

    private val TICKER_SNAPSHOT_SIZE = 11
    private val ORDERBOOK_SNAPSHOT_SIZE = 4

    override fun subscribeTicker(subscribeTickerRequest: SubscribeTickerRequest): Flowable<Array<String>> {
        bitfinexApi.openWebSocketEvent()
            .filter {
                it is WebSocket.Event.OnConnectionOpened<*>
            }
            .subscribe({
                bitfinexApi.sendTickerRequest(subscribeTickerRequest)
            }, { e ->
                Timber.e(e)
            })
        return bitfinexApi.observeTicker()
            .subscribeOn(Schedulers.io())
            .filter { it.size == TICKER_SNAPSHOT_SIZE }
    }

    override fun subscribeOrderBook(subscribeOrderBookRequest: SubscribeOrderBookRequest): Flowable<DoubleArray> {
        bitfinexApi.openWebSocketEvent()
            .filter {
                it is WebSocket.Event.OnConnectionOpened<*>
            }
            .subscribe({
                bitfinexApi.sendOrderBookRequest(subscribeOrderBookRequest)
            }, { e ->
                Timber.e(e)
            })

        return bitfinexApi.observeOrderBook()
            .subscribeOn(Schedulers.io())
            .filter { it.size == ORDERBOOK_SNAPSHOT_SIZE }
    }
}
