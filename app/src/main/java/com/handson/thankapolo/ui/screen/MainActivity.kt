package com.handson.thankapolo.ui.screen

import android.Manifest
import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
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
import androidx.core.content.ContextCompat
import com.handson.thankapolo.ThankApoloApplication.Companion.spfManager
import com.handson.thankapolo.ui.base.BaseActivity
import com.handson.thankapolo.ui.screen.login.LoginActivity
import com.handson.thankapolo.ui.screen.profile.ProfileActivity
import com.handson.thankapolo.ui.screen.splash.SplashScreen
import com.handson.thankapolo.ui.theme.ThankApoloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    companion object{
        private const val REQUEST_POST_NOTIFICATIONS_PERMISSIONS = 10
        private const val REQUIRED_POST_NOTIFICATIONS_PERMISSIONS = Manifest.permission.POST_NOTIFICATIONS
    }

    private val viewModel by viewModels<MainViewModel>()
    private val callback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            finishAffinity()
        }
    }
    override fun initScreen() {
        this.onBackPressedDispatcher.addCallback(callback)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2){
            if (ContextCompat.checkSelfPermission(this,
                    REQUIRED_POST_NOTIFICATIONS_PERMISSIONS
                ) != PackageManager.PERMISSION_GRANTED)
                 requestPermissions(arrayOf(REQUIRED_POST_NOTIFICATIONS_PERMISSIONS),
                REQUEST_POST_NOTIFICATIONS_PERMISSIONS)
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_POST_NOTIFICATIONS_PERMISSIONS){
            spfManager.setPushNotification(true)
        } else {
            spfManager.setPushNotification(false)
        }
    }
}
