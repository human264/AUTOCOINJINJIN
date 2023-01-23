package com.backgom.coco.network.model

import com.backgom.coco.dataModel.RecentPriceData

data class RecentCoinPriceList(

    val status: String,
    val data: List<RecentPriceData>

)