package com.backgom.coco.repository

import com.backgom.coco.network.Api
import com.backgom.coco.network.RetrofitInstance

class NetWorkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()

    suspend fun getInterestCoinPriceData(coin: String) = client.getRecentCoinPrice(coin)
}