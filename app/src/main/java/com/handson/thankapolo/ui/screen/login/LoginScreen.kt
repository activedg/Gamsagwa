package com.handson.thankapolo.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.thankapolo.ui.theme.Typography
import com.handson.thankapolo.ui.theme.seed
import com.handson.thankapolo.ui.theme.seed_70

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToSignUp : () -> Unit
){
    val colorScheme = MaterialTheme.colorScheme

    var userId = rememberSaveable{ mutableStateOf("") }
    var userPwd = rememberSaveable{ mutableStateOf("") }
    var pwdVisible = rememberSaveable { mutableStateOf(false)}

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorScheme.background,
            darkIcons = true
        )
    }

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(
                modifier = Modifier.height(140.dp))
            Text(
                text = "감사과",
                style = Typography.displayLarge
            )
            Text(
                text = "전하기 힘들었던 감사함과 미안함",
                style = Typography.headlineSmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(
                modifier = Modifier.height(100.dp)
            )
            OutlinedTextField(
                value = userId.value,
                onValueChange = {userId.value = it},
                label = { Text(text = "이메일")},
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "이메일", style = Typography.bodyLarge)}
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            OutlinedTextField(
                value = userPwd.value,
                onValueChange = {userPwd.value = it},
                label = { Text(text = "비밀번호")},
                placeholder = { Text(text = "비밀번호", style = Typography.bodyLarge)},
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (pwdVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                trailingIcon = {
                    val image = if (pwdVisible.value) Icons.Filled.VisibilityOff
                        else Icons.Filled.Visibility
                    IconButton(onClick = {
                        pwdVisible.value = !pwdVisible.value
                    }) {
                        Image(imageVector = image, contentDescription = null)
                    }

                }
            )
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Button(onClick = {viewModel.signIn(userId.value, userPwd.value)},
                colors = ButtonDefaults.buttonColors(seed),
                modifier = Modifier.fillMaxWidth(),
                enabled = userId.value.length >= 4 && userPwd.value.length >= 6
            ) {
                Text(text = "감사과에 로그인",
                    style = Typography.labelLarge,
                )
            }
            Spacer(
                modifier = Modifier.weight(1.0f)
            )
            Button(onClick = navigateToSignUp,
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            ) {
                Text(text = "회원가입",
                    style = Typography.labelLarge
                )
            }
        }
    }
}