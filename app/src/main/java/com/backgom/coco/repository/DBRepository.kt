package com.backgom.coco.repository

import com.backgom.coco.App
import com.backgom.coco.db.CoinPriceDatabase
import com.backgom.coco.db.entity.InterestCoinEntity
import com.backgom.coco.db.entity.SelectedCoinPriceEntity

class DBRepository {

    val context = App.context()
    val db = CoinPriceDatabase.getDatabase(context)

    fun getAllInterestCoinData() = db.interestCoinDAO().getAllData()

    fun insertInterestCoinData(interestCoinEntity: InterestCoinEntity)
     = db.interestCoinDAO().insert(interestCoinEntity)

    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity)
    =db.interestCoinDAO().update(interestCoinEntity)

    fun getAllInterestSelectedCoinData() = db.interestCoinDAO().getSelectedData()

    fun getAllCoinPriceData() = db.selectedCoinDAO().getAllData()

    fun insertCoinPriceData(selectedCoinPriceEntity: SelectedCoinPriceEntity)
    =db.selectedCoinDAO().insert(selectedCoinPriceEntity)

    fun getOneSelectedCoinData(coinName:String) = db.selectedCoinDAO().getOneCoinData(coinName)

}