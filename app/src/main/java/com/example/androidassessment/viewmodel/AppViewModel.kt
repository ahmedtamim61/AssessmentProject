package com.example.androidassessment.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidassessment.MainApplication
import com.example.androidassessment.dependencyinjection.AppComponent
import com.example.androidassessment.model.AppRepository
import javax.inject.Inject

class AppViewModel : ViewModel() {

    private val appRepository : AppRepository = MainApplication.appComponent.getAppRepository()

    fun fetchData() = appRepository.fetchData()
    fun getResponseLiveData() = appRepository.getResponseLiveData()

    override fun onCleared() {
        super.onCleared()
        appRepository.clear()
    }
}