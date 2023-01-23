package com.backgom.coco.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.backgom.coco.db.entity.SelectedCoinPriceEntity


@Dao
interface SelectedCoinPriceDAO {

    @Query("SELECT * FROM selected_coin_price_table")
    fun getAllData() : List<SelectedCoinPriceEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(selectedCoinPriceEntity: SelectedCoinPriceEntity)

    @Query("SELECT * FROM selected_coin_price_table WHERE coinName = :coinName")
    fun getOneCoinData(coinName: String):List<SelectedCoinPriceEntity>

}