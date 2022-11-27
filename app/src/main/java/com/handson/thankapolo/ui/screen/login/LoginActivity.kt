package com.handson.thankapolo.ui.screen.login

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.handson.thankapolo.navigation.LoginNavHost
import com.handson.thankapolo.ui.base.BaseActivity
import com.handson.thankapolo.ui.screen.MainActivity
import com.handson.thankapolo.ui.theme.ThankApoloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity(){
    override fun initScreen() {
        setContent {
            val navController = rememberNavController()
            ThankApoloTheme {
                LoginNavHost(
                    navController = navController,
                    onLogin = { moveToMain() },
                    context = this
                )
            }
        }
    }

    private fun moveToMain(){
        startActivity(Intent(this, MainActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }
}