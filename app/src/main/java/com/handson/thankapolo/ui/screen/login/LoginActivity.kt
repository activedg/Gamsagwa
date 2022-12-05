package com.handson.thankapolo.ui.screen.login

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.handson.thankapolo.navigation.LoginNavHost
import com.handson.thankapolo.ui.base.BaseActivity
import com.handson.thankapolo.ui.screen.MainActivity
import com.handson.thankapolo.ui.theme.ThankApoloTheme
import com.handson.thankapolo.utils.SharedPreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity(){
    private val viewModel by viewModels<LoginViewModel>()
    private val spfManager by lazy {
        SharedPreferenceUtil(this)
    }
    override fun initScreen() {
        setContent {
            val navController = rememberNavController()
            ThankApoloTheme {
                LoginNavHost(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            observe(navController)
        }
    }

    private fun moveToMain(){
        startActivity(Intent(this, MainActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        )
    }

    private fun observe(navController: NavHostController){
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.signUpData.collectLatest{
                        if (it.isNotEmpty()) navController.popBackStack()
                    }
                }
                launch {
                    viewModel.errorData.collectLatest {
                        if (it.isNotEmpty()) Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }
                launch {
                    viewModel.signInData.collectLatest {
                        if (it.isNotEmpty()){
                            spfManager.setUserToken(it)
                            moveToMain()
                        }
                    }
                }
            }
        }
    }
}