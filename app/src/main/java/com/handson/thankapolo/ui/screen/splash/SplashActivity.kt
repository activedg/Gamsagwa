package com.handson.thankapolo.ui.screen.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.thankapolo.ui.base.BaseActivity
import com.handson.thankapolo.ui.screen.login.LoginActivity
import com.handson.thankapolo.ui.theme.ThankApoloTheme
import com.handson.thankapolo.ui.theme.seed
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    override fun initScreen() {
        setContent {
            ThankApoloTheme {
                SplashScreen(onClickLogin = {moveToLogin()})
            }
        }
    }


    private fun moveToLogin(){
        startActivity(Intent(this, LoginActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThankApoloTheme {
        SplashScreen(onClickLogin = {})
    }
}
