package com.infullmobile.nasapod.api

import com.infullmobile.nasapod.model.AstronomyMedia
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodService {
    @GET("/fetchPicture") fun fetchPicture(@Query("date") date: String): Call<AstronomyMedia>
}
