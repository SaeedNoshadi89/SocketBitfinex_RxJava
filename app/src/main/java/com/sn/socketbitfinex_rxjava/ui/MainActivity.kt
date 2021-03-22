package com.sn.socketbitfinex_rxjava.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sn.socketbitfinex_rxjava.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.apply {
            viewModel.ticker.observe(this@MainActivity, { ticker ->
                    tvVolume.text = "volume: ${ticker.volume}"
                    tvLastPrice.text = "last price: ${ticker.lastPrice}"
                    tvLow.text = "low: ${ticker.low}"
                    tvHigh.text = "high: ${ticker.high}"
                    tvDailyChange.text = "change: ${ticker.dailyChange}"
            })

            viewModel.orderBooks.observe(this@MainActivity, { orderBookList ->
                orderBookBidList.adapter = OrderBookAdapter().apply {
                    asyncDiffer.submitList(getOrderBookBidList(orderBookList))
                }
                orderBookAskList.adapter = OrderBookAdapter().apply {
                    asyncDiffer.submitList(getOrderBookAskList(orderBookList))
                }
            })
        }

    }

}
