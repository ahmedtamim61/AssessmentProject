package com.example.androidassessment.dependencyinjection

import com.example.androidassessment.MainApplication
import com.example.androidassessment.model.AppRepository
import com.example.androidassessment.networkservice.NetworkDataSource
import com.example.androidassessment.viewmodel.AppViewModel
import com.example.androidassessment.viewmodel.AppViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [AppModule::class])
interface AppComponent {

    fun inject(appRepository: AppRepository)
    fun inject(networkDataSource: NetworkDataSource)
    fun inject(viewModelFactory: AppViewModelFactory)
    fun inject(viewModel : AppViewModel)
}