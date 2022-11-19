package com.handson.thankapolo.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.thankapolo.R
import com.handson.thankapolo.ui.theme.Typography
import com.handson.thankapolo.ui.theme.seed
import com.handson.thankapolo.ui.theme.seed_70

@Composable
fun SplashScreen(
    onClickLogin : () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = seed_70,
            darkIcons = false
        )
    }

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.splash_shape),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "감사과",
                modifier = Modifier.padding(top = 24.dp),
                color = Color.White,
                style = Typography.displayLarge
            )
            Image(
                painter = painterResource(id = R.drawable.ic_splash),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                                .padding(top = 50.dp)
                                .padding(horizontal = 50.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onClickLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 50.dp),
                colors = ButtonDefaults.buttonColors(seed)
            ) {
                Text(
                    "감사과에 로그인",
                    style = Typography.labelLarge
                )
            }
        }
    }
}