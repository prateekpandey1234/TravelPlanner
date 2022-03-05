package com.example.travelplanner.data.remote

data class ImageResponse(
    val ImageList: List<PlaceImage>,
    val total: Int, // 31318
    val totalImageList: Int // 500
)