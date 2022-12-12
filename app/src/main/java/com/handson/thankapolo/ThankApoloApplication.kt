package com.handson.thankapolo

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.os.Bundle
import com.handson.thankapolo.utils.SharedPreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThankApoloApplication:  Application(), Application.ActivityLifecycleCallbacks {
    companion object{
        lateinit var spfManager: SharedPreferenceUtil
        private lateinit var application: ThankApoloApplication
        fun getInstance() : ThankApoloApplication = application

        var currentActivity : Activity? = null
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        // spfManager
        spfManager = SharedPreferenceUtil(applicationContext)

        registerActivityLifecycleCallbacks(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        unregisterActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentActivity = activity
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}