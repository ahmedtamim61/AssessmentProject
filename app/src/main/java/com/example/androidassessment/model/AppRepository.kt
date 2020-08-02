package com.example.androidassessment.model

import androidx.lifecycle.MutableLiveData
import com.example.androidassessment.MainApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

object AppRepository {

    private val responseMutableLiveData : MutableLiveData<Model>? = MutableLiveData()

    fun fetchData() {
        var response : Response<Model>?
        CoroutineScope(Dispatchers.Default).launch {
            response = MainApplication.appComponent.getNetworkDataSource().fetchData()
            responseMutableLiveData?.postValue(response?.body())
        }
    }

    fun getResponseLiveData() = responseMutableLiveData

    fun clear() {
        responseMutableLiveData?.value = null
    }
}
