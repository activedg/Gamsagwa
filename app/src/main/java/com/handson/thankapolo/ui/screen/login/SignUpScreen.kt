package com.handson.thankapolo.ui.screen.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.thankapolo.ui.theme.Typography
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: LoginViewModel
){
    val colorScheme = MaterialTheme.colorScheme

    var userName = rememberSaveable { mutableStateOf("") }
    var userId = rememberSaveable { mutableStateOf("") }
    var userPwd = rememberSaveable { mutableStateOf("") }
    var userPwdCheck = rememberSaveable{ mutableStateOf("") }
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
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "회원가입",
                style = Typography.displayLarge
            )
            Text(
                text = "감사과에 회원가입을 진행합니다",
                style = Typography.headlineSmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(
                modifier = Modifier.height(60.dp)
            )
            OutlinedTextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                label = { Text(text = "이름") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "이름", style = Typography.bodyLarge) },
                supportingText = { if (userName.value.length < 2) Text(text = "이름은 두 글자 이상 이어야 합니다.", style = Typography.bodySmall)}
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            OutlinedTextField(
                value = userId.value,
                onValueChange = { userId.value = it },
                label = { Text(text = "아이디") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "아이디", style = Typography.bodyLarge)},
                supportingText = { if(userId.value.length < 4) Text(text = "아이디는 네 글자 이상 이어야 합니다.", style = Typography.bodySmall)}
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            OutlinedTextField(
                value = userPwd.value,
                onValueChange = { userPwd.value = it },
                label = { Text(text = "비밀번호") },
                placeholder = { Text(text = "비밀번호", style = Typography.bodyLarge) },
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

                },
                supportingText = { if(userPwd.value.length < 6) Text(text = "비밀번호는 여섯 글자 이상 이어야 합니다.", style = Typography.bodySmall)}
            )
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            OutlinedTextField(
                value = userPwdCheck.value,
                onValueChange = { userPwdCheck.value = it },
                label = { Text(text = "비밀번호 확인") },
                placeholder = { Text(text = "비밀번호 확인", style = Typography.bodyLarge) },
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

                },
                supportingText = {
                    if (userPwd.value.isNotEmpty()){
                        if (userPwd.value == userPwdCheck.value)
                            Text(text = "비밀번호와 일치합니다.", style = Typography.bodySmall)
                        else
                            Text(text = "비밀번호와 일치하지 않습니다", style = Typography.bodySmall, color = MaterialTheme.colorScheme.error)
                    }
                }
            )
            Spacer(
                modifier = Modifier.weight(1.0f)
            )
            Button(
                onClick = {viewModel.signUp(userId.value, userPwd.value, userName.value)},
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
                enabled = userName.value.length >= 2 && userId.value.length >= 4 && userPwd.value.length >= 6 && userPwd.value == userPwdCheck.value
            ) {
                Text(
                    text = "회원가입",
                    style = Typography.labelLarge
                )
            }
        }
    }
}