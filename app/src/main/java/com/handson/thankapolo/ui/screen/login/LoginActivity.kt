package com.handson.thankapolo.ui.screen.login

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.handson.thankapolo.ui.base.BaseActivity
import com.handson.thankapolo.ui.screen.MainActivity
import com.handson.thankapolo.ui.theme.ThankApoloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity(){
    override fun initScreen() {
        setContent {
            ThankApoloTheme {
                LoginScreen(onLogin = { moveToMain() })
            }
        }
    }

    private fun moveToMain(){
        startActivity(Intent(this, MainActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }
}