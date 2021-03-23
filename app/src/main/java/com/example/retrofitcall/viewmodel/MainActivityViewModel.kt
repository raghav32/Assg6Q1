package com.example.retrofitcall.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcall.models.RecyclerList
import com.example.retrofitcall.network.RetroInstance
import com.example.retrofitcall.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel:ViewModel() {

    lateinit var recyclerListLiveData:MutableLiveData<RecyclerList>

    init {
        recyclerListLiveData= MutableLiveData()
    }

    fun getRecyclerListObserver():MutableLiveData<RecyclerList>{
        return recyclerListLiveData
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance=RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response=retroInstance.getDataFromAPI("ny")
            recyclerListLiveData.postValue(response)

        }
    }
}