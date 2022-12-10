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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(4.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = { expandedState.value = !expandedState.value },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .padding(start = 16.dp, end = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier
                            .size(40.dp),
                        color = seed,
                    ) {
                        Box(
                            modifier = Modifier
                            .fillMaxSize(1.0f),
                            contentAlignment = Center
                        ) {
                            Text(text = message.senderNickName.substring(0, 1),
                                style = Typography.bodyLarge,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = message.title, style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.weight(1f))
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more")
                    }

                }
                AnimatedVisibility(visible = expandedState.value) {
                    Column() {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(text = message.senderNickName, style = Typography.bodyLarge)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(text = message.description, style = Typography.bodyMedium)
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(4.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMessageCard(){
    LetterCard(message = Message(1, false, "테스트테스트",
        "THANK", "익명", false, "테스트"))
}

