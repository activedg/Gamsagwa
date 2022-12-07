package com.handson.thankapolo.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.handson.domain.data.home.Message
import com.handson.thankapolo.ui.theme.Typography
import com.handson.thankapolo.ui.theme.seed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetterCard(
    message: Message
){
    var expandedState = rememberSaveable { mutableStateOf(false) }

    // Todo : 임시로 message 타입에 아무거나 넣어놈
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { expandedState.value = !expandedState.value },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(40.dp),
                color = seed
            ) {

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1.0f)
            ) {
                Text(text = message.title, style = Typography.bodyLarge)
                Text(text = message.title, style = Typography.bodyMedium)
                AnimatedVisibility(visible = expandedState.value){
                    Column() {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = message.messageType)
                    }
                }
            }
            Text(text = "10:30", style = Typography.bodyMedium, color = Color.Gray)

        }

    }
}

