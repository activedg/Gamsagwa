package com.handson.thankapolo.ui.screen

import android.Manifest
import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.handson.thankapolo.ThankApoloApplication.Companion.spfManager
import com.handson.thankapolo.ui.base.BaseActivity
import com.handson.thankapolo.ui.screen.home.ThankApoloScreen
import com.handson.thankapolo.ui.screen.home.ThankApoloViewModel
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

    private var backKeyPressedTime: Long = 0

    private val viewModel by viewModels<ThankApoloViewModel>()

    private val callback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                // 뒤로가기 두 번 누르면 종료
                finishAffinity()
            } else{
                backKeyPressedTime = System.currentTimeMillis()
                Toast.makeText(this@MainActivity, "뒤로 가기 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            }
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
            ThankApoloTheme {
                ThankApoloScreen(viewModel = viewModel, moveToProfile = { moveToProfile() })
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

    override fun onResume() {
        super.onResume()
        viewModel.getNickname()
    }

    fun showNewMessageToast(){
        runOnUiThread {
            viewModel.getMessageData()
        }
    }
}
