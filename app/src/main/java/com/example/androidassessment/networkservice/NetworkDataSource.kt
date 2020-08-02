package com.example.androidassessment.networkservice

import com.example.androidassessment.model.Model
import retrofit2.Response

class NetworkDataSource (private val retrofitService: RetrofitService) {

    companion object {
        @Volatile private var INSTANCE: NetworkDataSource? = null

        fun getInstance(retrofitService: RetrofitService): NetworkDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: NetworkDataSource(retrofitService).also { INSTANCE = it }
            }
    }

    suspend fun fetchData() : Response<Model> = retrofitService.fetchData()

}