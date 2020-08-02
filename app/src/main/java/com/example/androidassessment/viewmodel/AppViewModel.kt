package com.example.androidassessment.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidassessment.MainApplication
import com.example.androidassessment.model.AppRepository

class AppViewModel : ViewModel() {

    private val appRepository : AppRepository = MainApplication.appComponent.getAppRepository()

    fun fetchData() = appRepository.fetchData()
    fun getResponseLiveData() = appRepository.getResponseLiveData()
    fun getErrorLiveData() = appRepository.getErrorLiveData()

    override fun onCleared() {
        super.onCleared()
        appRepository.clear()
    }
}