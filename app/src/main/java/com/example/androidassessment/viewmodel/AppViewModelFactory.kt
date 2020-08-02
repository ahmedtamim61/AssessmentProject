package com.example.androidassessment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidassessment.MainApplication
import com.example.androidassessment.model.AppRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AppViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var appRepository: AppRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        MainApplication.appComponent.inject(this)
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(appRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}