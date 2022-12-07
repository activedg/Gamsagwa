package com.handson.thankapolo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.handson.thankapolo.ui.theme.Typography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConfirmDialog(
    title: String,
    content: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
){
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
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
            IconButton(onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp)) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
            Text(text = title, style = Typography.headlineSmall)
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = content, style = Typography.bodyMedium)
            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = onDismiss, modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onSecondary)
                ) {
                    Text(text = "취소", style = Typography.labelMedium, color = Color.Black)
                }
                Button(onClick = onConfirm, modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)) {
                    Text(text = "확인", style = Typography.labelMedium)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}