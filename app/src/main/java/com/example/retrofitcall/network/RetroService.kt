package com.example.retrofitcall.network

import com.example.retrofitcall.models.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("repositories")
    suspend fun getDataFromAPI(@Query("q") query:String):RecyclerList
}