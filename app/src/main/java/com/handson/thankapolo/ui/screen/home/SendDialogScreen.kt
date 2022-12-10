package com.handson.thankapolo.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.handson.thankapolo.ui.theme.Typography

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SendDialog(
    onDismiss: () -> Unit
){
    val categorySelect = rememberSaveable() {
        mutableStateOf(0)
    }


    val receiver = rememberSaveable() {
        mutableStateOf("")
    }
    val title = rememberSaveable() {
        mutableStateOf("")
    }
    val content = rememberSaveable() {
        mutableStateOf("")
    }


    val anonymous = rememberSaveable {
        mutableStateOf(true)
    }

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current


    Dialog(onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false),
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 90.dp, top = 12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()) {
                Row() {
                    ElevatedFilterChip(selected = categorySelect.value == 1, onClick = { categorySelect.value = 1 },
                        label = { Text(text = "감사함", style = Typography.labelMedium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                        )},
                        leadingIcon = if (categorySelect.value == 1) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Localized Description",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        },

                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    ElevatedFilterChip(selected = categorySelect.value == 2, onClick = { categorySelect.value = 2 },
                        label = { Text(text = "미안함", style = Typography.labelMedium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                        )},
                        leadingIcon = if (categorySelect.value == 2) {
                            {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Localized Description",
                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                )
                            }
                        } else {
                            null
                        },
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "받는사람", style = Typography.bodyLarge)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        BasicTextField(
                            value = receiver.value, onValueChange = {receiver.value = it},
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                    keyboardController?.hide()
                                }
                            ),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                                .onKeyEvent { keyEvent ->
                                    if (keyEvent.key != Key.Enter || keyEvent.key != Key.SystemNavigationDown) return@onKeyEvent false
                                    keyboardController?.hide()
                                    focusManager.clearFocus()
                                    true
                                },
                            singleLine = true,

                            decorationBox = { innerTextField ->
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .focusRequester(focusRequester)
                                ) {
                                    if (receiver.value.isEmpty()) {
                                        Text(text = "이메일이나 이름을 입력해주세요.", style = Typography.bodyLarge, color = Color.Gray)
                                    }
                                }
                                innerTextField()
                            },
                            textStyle = Typography.bodyLarge,
                        )
                        Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "제목", style = Typography.bodyLarge)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        BasicTextField(
                            value = title.value, onValueChange = {title.value = it},
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                    keyboardController?.hide()},
                            ),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                                .onKeyEvent { keyEvent ->
                                    if (keyEvent.key != Key.Enter || keyEvent.key != Key.SystemNavigationDown) return@onKeyEvent false
                                    keyboardController?.hide()
                                    focusManager.clearFocus()
                                    true
                                },
                            singleLine = true,

                            decorationBox = { innerTextField ->
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .focusRequester(focusRequester)
                                ) {
                                    if (title.value.isEmpty()) {
                                        Text(text = "제목", style = Typography.bodyLarge, color = Color.Gray)
                                    }
                                }
                                innerTextField()
                            },
                            textStyle = Typography.bodyLarge,

                        )
                        Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(text = "익명으로 보내기", style = Typography.bodyLarge)
                    Checkbox(checked = anonymous.value, onCheckedChange = {anonymous.value = !anonymous.value})
                }

                BasicTextField(
                    value = content.value, onValueChange = {content.value = it},
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .weight(1f) ,
                    decorationBox = { innerTextField ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                        ) {
                            if (content.value.isEmpty()) {
                                Text(text = "내용", style = Typography.bodyLarge, color = Color.Gray)
                            }
                        }
                        innerTextField()
                    },
                    textStyle = Typography.bodyLarge
                )
                Row(modifier = Modifier.align(Alignment.End)) {
                    Button(onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)) {
                        Text(text = "취소", style = Typography.labelMedium, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(onClick = { /*TODO*/ }, enabled = categorySelect.value > 0 &&
                            title.value.isNotEmpty() && content.value.isNotEmpty()
                    ) {
                        Text(text = "전송", style = Typography.labelMedium)
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSendScreen(){
    SendDialog(onDismiss = {})
}