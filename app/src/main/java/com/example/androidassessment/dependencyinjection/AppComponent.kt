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

    fun getNetworkDataSource() : NetworkDataSource
    fun getAppRepository() : AppRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MainApplication) : AppComponent.Builder
        fun build() : AppComponent
    }

    fun inject(mainApplication: MainApplication)
}