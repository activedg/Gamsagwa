package com.handson.thankapolo.ui.screen.login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.handson.thankapolo.ui.base.BaseActivity
import com.handson.thankapolo.ui.theme.ThankApoloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity(){
    override fun initScreen() {
        setContent {
            ThankApoloTheme {
                LoginScreen()
            }
        }
    }
}