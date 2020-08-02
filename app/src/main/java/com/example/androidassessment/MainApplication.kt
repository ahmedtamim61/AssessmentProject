package com.example.androidassessment

import android.app.Application
import com.example.androidassessment.dependencyinjection.AppComponent
import com.example.androidassessment.dependencyinjection.AppModule
import com.example.androidassessment.dependencyinjection.DaggerAppComponent

class MainApplication : Application() {

    companion object {
        lateinit var appComponent : AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule())
            .build()
    }
}