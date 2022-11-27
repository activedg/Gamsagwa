package com.handson.thankapolo.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.handson.thankapolo.ui.screen.login.LoginScreen
import com.handson.thankapolo.ui.screen.login.SignUpScreen

@Composable
fun LoginNavHost(
    navController: NavHostController,
    onLogin : () -> Unit,
    context: Context,
    modifier: Modifier = Modifier
){
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){ LoginScreen(
            onLogin = onLogin,
            navigateToSignUp = {
                navController.navigate("signUp")
            }
        ) }
        composable("signUp") { SignUpScreen(
            onSignUp = {
                navController.popBackStack().also {
                    Toast.makeText(context, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )}
    }
}