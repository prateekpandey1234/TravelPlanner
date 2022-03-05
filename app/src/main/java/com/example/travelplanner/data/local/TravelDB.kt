package com.example.travelplanner.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TravelItem::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class TravelDB :RoomDatabase(){
    abstract fun traveldao():TravellDAO
}