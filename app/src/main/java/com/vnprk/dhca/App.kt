package com.vnprk.dhca

import android.app.Application
import com.vnprk.dhca.di.ApplicationComponent
import com.vnprk.dhca.di.DaggerApplicationComponent

public class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerApplicationComponent.builder()
            .applicationContext(applicationContext)
            .build()
    }
}