package com.infullmobile.nasapod.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.infullmobile.nasapod.api.ApodService
import com.infullmobile.nasapod.model.AstronomyMedia
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivityViewModel: ViewModel() {
    val picture = MutableLiveData<AstronomyMedia>()
    private val apodService: ApodService
    private val dateKey: String
        get() {
            val calendar = Calendar.getInstance()
            calendar.time = Date()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            return "$year-$month-$day"
        }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://us-central1-nasa-pictures.cloudfunctions.net/")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apodService = retrofit.create(ApodService::class.java)
    }

    fun fetchPicture() {
        apodService.fetchPicture(dateKey)
                .enqueue(object : Callback<AstronomyMedia> {
                    override fun onFailure(call: Call<AstronomyMedia>?, t: Throwable?) {
                        print(t)
                    }

                    override fun onResponse(call: Call<AstronomyMedia>, response: Response<AstronomyMedia>) {
                        response.body().let { picture.postValue(it) }
                    }
                })
    }
}
