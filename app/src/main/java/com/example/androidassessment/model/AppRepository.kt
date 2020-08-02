package com.example.androidassessment.model

import androidx.lifecycle.MutableLiveData
import com.example.androidassessment.MainApplication
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

object AppRepository {

    private val responseLiveData : MutableLiveData<Model>? = MutableLiveData()
    private val errorLiveData : MutableLiveData<Boolean?>? = MutableLiveData()

    fun fetchData() {
        var response : Response<Model>?
        CoroutineScope(Dispatchers.IO).launch {
            try {
                response = MainApplication.appComponent.getNetworkDataSource().fetchData()
                if (response?.code()?.equals(200)!! && response?.body() != null)
                    responseLiveData?.postValue(response?.body())
                else errorLiveData?.postValue(true)
            } catch (exception : HttpException) {
                errorLiveData?.postValue(true)
            } catch (e : Exception) {
                errorLiveData?.postValue(true)
            }
        }
    }

    fun getResponseLiveData() = responseLiveData
    fun getErrorLiveData() = errorLiveData

    fun clear() {
        responseLiveData?.value = null
        errorLiveData?.value = null
    }
}
