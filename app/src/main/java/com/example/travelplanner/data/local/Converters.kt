package com.example.travelplanner.data.local

import androidx.room.TypeConverter
import com.example.travelplanner.data.remote.PlaceImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type


class Converters :Serializable{
    @TypeConverter
    fun fromImageList(imageList: List<PlaceImage>): String {
        return Gson().toJson(imageList)
    }
    @TypeConverter
    fun toImageList( value:String):List<PlaceImage>{
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value,listType)
    }

}