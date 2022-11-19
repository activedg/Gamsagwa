package com.handson.thankapolo.ui.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.handson.thankapolo.ui.screen.login.LoginActivity
import com.handson.thankapolo.ui.screen.splash.SplashScreen
import com.handson.thankapolo.ui.theme.ThankApoloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThankApoloTheme {
                SplashScreen(onClickLogin = {startActivity(Intent(this, LoginActivity::class.java))})
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
            }
        }
    }
}