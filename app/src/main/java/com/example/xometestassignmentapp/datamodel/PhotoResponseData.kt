package com.example.xometestassignmentapp.datamodel

import com.google.gson.annotations.SerializedName

data class PhotosSearchResponse(
    val photos: PhotosListData
)

data class PhotosListData(
    val page: Int,
    val photo: List<PhotoResponse>
)

data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)
