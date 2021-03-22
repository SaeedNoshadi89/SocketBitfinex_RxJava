package com.sn.socketbitfinex_rxjava.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sn.domain.util.UseCase
import com.sn.socketbitfinex_rxjava.UseCases
import com.sn.socketbitfinex_rxjava.model.OrderBook
import com.sn.socketbitfinex_rxjava.model.Ticker
import com.sn.socketbitfinex_rxjava.model.toOrderBookModel
import com.sn.socketbitfinex_rxjava.model.toTickerModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _ticker = MutableLiveData<Ticker>()
    val ticker: LiveData<Ticker>
        get() = _ticker

    private val _orderBooks = MutableLiveData<List<OrderBook>>()
    val orderBooks: LiveData<List<OrderBook>>
        get() = _orderBooks

    init {
        compositeDisposable.add(useCases.observeTickerUseCase.execute(UseCase.None())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { tickerData -> tickerData.toTickerModel() }
            .subscribe({ ticker ->
                _ticker.postValue(ticker)
            }, { e ->
                Timber.e("observeTickerUseCase error: %s", e.toString())
            })
        )

        compositeDisposable.add(useCases.observeOrderBookUseCase.execute(UseCase.None())
            .subscribeOn(Schedulers.io())
            .map { orderBookData -> orderBookData.toOrderBookModel() }
            .map { orderBook ->
                orderBook.buildOrderBooks()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ orderBookList ->
                _orderBooks.postValue(ArrayList(orderBookList))
            }, { e ->
                Timber.e("observeOrderBookUseCase error: %s", e.toString())
            })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}
