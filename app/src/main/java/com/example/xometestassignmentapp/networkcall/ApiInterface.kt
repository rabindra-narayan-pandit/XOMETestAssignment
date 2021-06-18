package com.example.xometestassignmentapp.networkcall

import com.example.xometestassignmentapp.datamodel.PhotosSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1&safe_search=1")
    suspend fun fetchImages(@Query("text") text: String): PhotosSearchResponse
}