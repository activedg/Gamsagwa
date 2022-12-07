package com.handson.thankapolo.ui.screen.profile

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.SwitchLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.handson.thankapolo.ThankApoloApplication.Companion.spfManager
import com.handson.thankapolo.component.ConfirmDialog
import com.handson.thankapolo.component.NicknameCahngeDialog
import com.handson.thankapolo.ui.screen.login.LoginActivity
import com.handson.thankapolo.ui.theme.Typography
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    finish : () -> Unit,
    logout : (Boolean) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
){
    val nicknameDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val logoutDialogVisible = rememberSaveable{
        mutableStateOf(false)
    }
    val withdrawalDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }

    val isNotification = rememberSaveable {
        mutableStateOf(spfManager.getPushNotification())
    }

    val nickname : String? by viewModel.nicknameData.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "프로필", style = Typography.headlineMedium)},
                navigationIcon = { IconButton(onClick = finish) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "back",)
                }}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = nickname ?: "", style = Typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold), modifier = Modifier.weight(1f)
                )

                if (!nickname.isNullOrEmpty()){
                    Button(onClick = { nicknameDialogVisible.value = true }, colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer))
                    {
                        Text(text = "수정", style = Typography.labelMedium,
                            color = Color.Black
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(60.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Color.White)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "푸시 알림", modifier = Modifier.weight(1f), style = Typography.bodyLarge)
                Switch(checked = isNotification.value, onCheckedChange = {isNotification.value = it},
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Color.White)
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "만든 사람들", modifier = Modifier.weight(1f), style = Typography.bodyLarge)
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Color.White)
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "로그아웃", style = Typography.bodyLarge, color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { logoutDialogVisible.value = true })
                )
                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp))
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "회원탈퇴", style = Typography.bodyLarge, color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { withdrawalDialogVisible.value = true })
                )
            }
            Spacer(modifier = Modifier.height(80.dp))

        }

        // Nickname Dialog
        if (nicknameDialogVisible.value){
            NicknameCahngeDialog(onNicknameChange = {}, onDismissRequest = {nicknameDialogVisible.value = false})
        }

        // Logout Dialog
        if (logoutDialogVisible.value){
            ConfirmDialog(title = "로그아웃" , content = "정말로 로그아웃 하시겠습니까?",
                onConfirm = {logout(false)}, onDismiss = { logoutDialogVisible.value = false })
        }

        // Withdrawal Dialog
        if (withdrawalDialogVisible.value){
            ConfirmDialog(title = "회원탈퇴", content = "정말로 회원할퇴 하시겠습니까?",
                onConfirm = {
                    viewModel.removeUser()
                    logout(true) },
                onDismiss = {withdrawalDialogVisible.value = false})
        }
    }
}
