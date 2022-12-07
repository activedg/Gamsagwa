package com.handson.thankapolo

import android.app.Application
import com.handson.thankapolo.utils.SharedPreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThankApoloApplication:  Application(){
    companion object{
        lateinit var spfManager: SharedPreferenceUtil
        private lateinit var application: ThankApoloApplication
        fun getInstance() : ThankApoloApplication = application
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        // spfManager
        spfManager = SharedPreferenceUtil(applicationContext)
    }
}