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
import com.handson.thankapolo.ui.theme.Typography
import com.handson.thankapolo.ui.theme.seed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetterCard(){
    var expandedState = rememberSaveable { mutableStateOf(false) }

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
                Text(text = "보낸 사람", style = Typography.bodyLarge)
                Text(text = "제목", style = Typography.bodyMedium)
                AnimatedVisibility(visible = expandedState.value){
                    Column() {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "test test test test test test\ntest test test test test test\ntest test test test test test\n")
                    }
                }
            }
            Text(text = "10:30", style = Typography.bodyMedium, color = Color.Gray)

        }

    }
}