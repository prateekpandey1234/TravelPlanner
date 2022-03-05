package com.example.travelplanner.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TravellDAO {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertTravelitem(travelItem: TravelItem)

    @Delete
    suspend fun deletetravelitem(travelItem:TravelItem)

    @Query("SELECT * FROM Travel_table")
    fun getTravelitem():LiveData<List<TravelItem>>
}