package com.handson.thankapolo.ui.screen.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.thankapolo.ui.Greeting
import com.handson.thankapolo.ui.theme.ThankApoloTheme
import com.handson.thankapolo.ui.theme.seed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThankApoloTheme {
        SplashScreen()
    }
}