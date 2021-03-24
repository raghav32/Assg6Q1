package com.example.retrofitcall.models

import com.google.gson.annotations.SerializedName


data class RecyclerList(val items: ArrayList<RecyclerData>)
data class RecyclerData(val name:String,val description:String,val owner:Owner)

data class Owner(
    @SerializedName("avatar_url")
    val avatar_url:String)