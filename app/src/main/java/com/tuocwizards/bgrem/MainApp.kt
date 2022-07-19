package com.tuocwizards.bgrem

import android.app.Application
import android.content.Context
import com.tuocwizards.bgrem.di.DaggerMainComponent
import com.tuocwizards.bgrem.di.MainComponent

class MainApp: Application() {

    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.create()
    }
}

val Context.mainComponent: MainComponent
    get() = when(this) {
        is MainApp -> mainComponent
        else ->this.applicationContext.mainComponent
    }

