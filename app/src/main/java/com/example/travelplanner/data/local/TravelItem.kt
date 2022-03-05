package com.example.travelplanner.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.travelplanner.data.remote.PlaceImage

/**List: A generic ordered collection of elements. Methods in this interface support only read-only access to the list; read/write access is supported through the MutableList interface.

MutableList: A generic ordered collection of elements that supports adding and removing elements.*/
@Entity(tableName = "Travel_table")
class TravelItem (
        val City:String,
        val State:String,
        val Country:String,
        @PrimaryKey(autoGenerate = true)
        val Id:Int,
        val ImageList:List<PlaceImage>
        )