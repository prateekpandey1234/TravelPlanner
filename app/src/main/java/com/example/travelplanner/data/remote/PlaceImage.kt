package com.example.travelplanner.data.remote

import androidx.room.PrimaryKey

data class PlaceImage(
    val collections: Int, // 4231
    val comments: Int, // 121
    val downloads: Int, // 197026
    val id: Int, // 1127174
    val imageHeight: Int, // 3168
    val imageSize: Int, // 3922163
    val imageWidth: Int, // 4752
    val largeImageURL: String, // https://pixabay.com/get/ga16f4e152caafc60d0d95c7e5d7f3b43073eb1fc50829f0ab8b38219a87c62ae19062835b1e6877da6ace183824231539d8e9cf2b13d2875fc78bf5bafd4f285_1280.jpg
    val likes: Int, // 895
    val pageURL: String, // https://pixabay.com/photos/sunflower-flower-plant-petals-1127174/
    val previewHeight: Int, // 99
    val previewURL: String, // https://cdn.pixabay.com/photo/2016/01/08/05/24/sunflower-1127174_150.jpg
    val previewWidth: Int, // 150
    val tags: String, // sunflower, flower, plant
    val type: String, // photo
    val user: String, // mploscar
    val userImageURL: String, // https://cdn.pixabay.com/user/2016/01/05/14-08-20-943_250x250.jpg
    @PrimaryKey(autoGenerate = true)
    val user_id: Int, // 1445608
    val views: Int, // 324477
    val webformatHeight: Int, // 426
    val webformatURL: String, // https://pixabay.com/get/g56130f57e7c38989b2288bf31aff0f14f541d41a29594c03a612d2c3b0d4622b8d7130d46ee32c62526b4057f64e9f130f8882782ba85333e07cd6fd85254e3b_640.jpg
    val webformatWidth: Int // 640
)