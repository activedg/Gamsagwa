package com.handson.thankapolo.ui.screen

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.handson.thankapolo.ui.base.BaseActivity
import com.handson.thankapolo.ui.screen.login.LoginActivity
import com.handson.thankapolo.ui.screen.profile.ProfileActivity
import com.handson.thankapolo.ui.screen.splash.SplashScreen
import com.handson.thankapolo.ui.theme.ThankApoloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private val callback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            finishAffinity()
        }
    }
    override fun initScreen() {
        this.onBackPressedDispatcher.addCallback(callback)
        setContent {
            val name by viewModel.nicknameData.collectAsState()
            ThankApoloTheme {
                MainScreen(name = name, moveToProfile = {moveToProfile()})
            }
        }
    }

    private fun moveToProfile(){
        startActivity(Intent(this, ProfileActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }
}
