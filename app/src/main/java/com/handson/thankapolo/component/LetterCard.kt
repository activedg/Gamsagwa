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
import androidx.compose.runtime.remember
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
import com.handson.thankapolo.ui.theme.sorry_color
import com.handson.thankapolo.ui.theme.thank_color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LetterCard(
    message: Message,
    onHide: (Message) -> Unit,
    onDelete: (Message) -> Unit
){
    var expandedState = rememberSaveable { mutableStateOf(false) }

    val dropDownExpanded = remember { mutableStateOf(false)}

    Column(modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = if (message.hidden) Color.LightGray else Color.White,),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = { expandedState.value = !expandedState.value },
            modifier = Modifier
                .fillMaxWidth(),
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
                        color = if (message.messageType == "THANK") thank_color else sorry_color,
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
                    Column() {
                        IconButton(onClick = { dropDownExpanded.value = !dropDownExpanded.value }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more")
                        }
                        DropdownMenu(expanded = dropDownExpanded.value, onDismissRequest = { dropDownExpanded.value = false },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            DropdownMenuItem(text = { Text(text = if (message.hidden) "숨김 해제" else "숨김", style = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
                            )},
                                onClick = {
                                    onHide(message)
                                    dropDownExpanded.value = false
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                            DropdownMenuItem(text = { Text(text = "삭제", style = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
                            )},
                                onClick = {
                                    onDelete(message)
                                    dropDownExpanded.value = false
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                AnimatedVisibility(visible = expandedState.value, modifier = Modifier.padding(end = 12.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(text = if (message.nameBlind || message.senderNickName == "익명") "익명으로 보냄"
                            else "보낸 사람 : ${message.senderNickName}" , style = Typography.bodyLarge)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(text = message.description, style = Typography.bodyMedium)
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(onClick = { expandedState.value = false }, modifier = Modifier.align(Alignment.End)) {
                            Text(text = "확인")
                        }
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
