package com.handson.thankapolo.ui.screen.profile

import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.handson.thankapolo.ThankApoloApplication.Companion.spfManager
import com.handson.thankapolo.ui.base.BaseActivity
import com.handson.thankapolo.ui.screen.login.LoginActivity
import com.handson.thankapolo.ui.theme.ThankApoloTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {

    override fun initScreen() {
        setContent {
            ThankApoloTheme {
                ProfileScreen(finish = {finish()}, logout = {logout()})
            }
        }
    }

    private fun logout(){
        spfManager.removeUserToken()
        Toast.makeText(this, "로그아웃을 완료하였습니다", Toast.LENGTH_SHORT).show()
        finishAffinity()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}