package com.backgom.coco.db.dao

import androidx.room.*
import com.backgom.coco.db.entity.InterestCoinEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface InterestCoinDAO {
    //getAllData
    // 데이터의 변경 사항을 감지하기 좋다.
    @Query("SELECT * FROM interest_coin_table")
    fun getAllData() : Flow<List<InterestCoinEntity>>

    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(interestCoinEntity: InterestCoinEntity)

    //update
    @Update
    fun update(interestCoinEntity: InterestCoinEntity)

    //getSelectedCoinList
    @Query("SELECT * FROM interest_coin_table WHERE selected = :selected")
    fun getSelectedData(selected:Boolean = true) : List<InterestCoinEntity>


}