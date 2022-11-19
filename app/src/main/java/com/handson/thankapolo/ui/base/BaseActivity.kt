package com.handson.thankapolo.ui.base

import android.os.Bundle
import android.transition.Fade
import android.view.Window
import androidx.activity.ComponentActivity

abstract class BaseActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window){
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

            // Fade 전환
            enterTransition = Fade().apply {
                excludeTarget(android.R.id.navigationBarBackground, true)
            }

            exitTransition = Fade().apply {
                excludeTarget(android.R.id.navigationBarBackground, true)
            }
            initScreen()
        }
    }
    abstract fun initScreen()
}