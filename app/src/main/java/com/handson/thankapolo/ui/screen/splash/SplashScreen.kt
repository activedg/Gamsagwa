package com.handson.thankapolo.ui.screen.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.thankapolo.R
import com.handson.thankapolo.component.ArrowImage
import com.handson.thankapolo.ui.theme.Typography
import com.handson.thankapolo.ui.theme.seed
import com.handson.thankapolo.ui.theme.seed_70
import kotlinx.coroutines.delay

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

//     //Todo : 애니메이션 뺄지 말지
//    val infiniteTransition = rememberInfiniteTransition()
//    val xOffset = infiniteTransition.animateFloat(
//        initialValue = -400f,
//        targetValue = 400f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(
//                durationMillis = 2000,  delayMillis = 1000, easing = LinearEasing
//            )
//        )
//    )

//    var startAnimation by remember { mutableStateOf(false) }
//    val alphaAnim = animateFloatAsState(
//        targetValue = if (startAnimation) 1f else 0f,
//        animationSpec = tween(
//            durationMillis = 1000
//        )
//    )

//    LaunchedEffect(key1 = true){
//        startAnimation = true
//    }


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
                modifier = Modifier.padding(top = 40.dp),
                color = Color.White,
                style = Typography.displayLarge
            )
            Text(
                text = "전하기 힘들었던 감사함과 미안함",
                color = Color.White,
                style = Typography.headlineSmall,
                modifier = Modifier.padding(top = 8.dp)
            )
//            Spacer(
//                modifier = Modifier.height(50.dp)
//            )
//            Image(
//                painter = painterResource(id = R.drawable.ic_splash),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 50.dp),
//                contentScale = ContentScale.FillWidth
//            )
            Spacer(
                modifier = Modifier.height(50.dp)
            )
//            ArrowImage(
//                painterResource = R.drawable.ic_splash2,
//                xOffset = xOffset.value,
//                modifier = Modifier.size(250.dp),
//            )
            Image(
                painter = painterResource(id = R.drawable.ic_splash2),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp),
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