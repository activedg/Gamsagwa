package com.handson.thankapolo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThankApoloApplication:  Application(){
    companion object{
        private lateinit var application: ThankApoloApplication
        fun getInstance() : ThankApoloApplication = application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}