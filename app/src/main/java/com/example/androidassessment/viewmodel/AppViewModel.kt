package com.example.androidassessment.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidassessment.MainApplication
import com.example.androidassessment.model.AppRepository
import javax.inject.Inject

class AppViewModel @Inject constructor(private val appRepository : AppRepository) : ViewModel() {

    init { MainApplication.appComponent.inject(this) }

    fun fetchData() = appRepository.fetchData()
    fun getResponseLiveData() = appRepository.getResponseLiveData()
    fun getErrorLiveData() = appRepository.getErrorLiveData()

    override fun onCleared() {
        super.onCleared()
        appRepository.clear()
    }
}