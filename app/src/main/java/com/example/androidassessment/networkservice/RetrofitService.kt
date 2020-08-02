package com.example.androidassessment.networkservice

import com.example.androidassessment.model.Model
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    suspend fun fetchData() : Response<Model>
}