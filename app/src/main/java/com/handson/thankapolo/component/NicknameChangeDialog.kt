package com.handson.thankapolo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.handson.thankapolo.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NicknameChangeDialog(
    onNicknameChange : (String) -> Unit,
    onDismissRequest : () -> Unit
){
    val nickname = rememberSaveable {
        mutableStateOf("")
    }
    Dialog(onDismissRequest = onDismissRequest, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            IconButton(onClick = onDismissRequest,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp)) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
            Text(text = "닉네임 변경", style = Typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = nickname.value,
                onValueChange = { nickname.value = it },
                label = { Text(text = "닉네임") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "닉네임", style = Typography.bodyLarge) },
                supportingText = { if (nickname.value.length < 2) Text(text = "닉네임은 두 글자 이상 이어야 합니다.", style = Typography.bodySmall)
                    else if (nickname.value.length > 6) Text(text = "닉네임은 여섯 글자 이하 이어야 합니다.", style = Typography.bodySmall)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { onNicknameChange(nickname.value) }, enabled = (nickname.value.length in 2..6))
            {
                Text(text = "수정", style = Typography.labelMedium,)
            }
            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}