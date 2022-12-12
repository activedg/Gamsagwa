package com.handson.thankapolo.ui.screen.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.domain.data.home.Message
import com.handson.thankapolo.R
import com.handson.thankapolo.component.ConfirmDialog
import com.handson.thankapolo.component.LetterCard
import com.handson.thankapolo.component.ThankApoloTab
import com.handson.thankapolo.navigation.BottomNavigationBar
import com.handson.thankapolo.navigation.NavigationItem
import com.handson.thankapolo.ui.theme.Typography
import com.handson.thankapolo.ui.theme.seed
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThankApoloScreen(
    moveToProfile : () -> Unit,
    viewModel : ThankApoloViewModel
){
    val name : String? by viewModel.nicknameData.collectAsState()

    val context = LocalContext.current

    // 삭제 다이얼로그
    val removeDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val hideDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }

    // 숨김 상태
    val hideVisible = rememberSaveable{
        mutableStateOf(false)
    }

    val messageList : MutableList<Message>? by viewModel.letterData.collectAsState()

    LaunchedEffect(Unit){
        viewModel.toastMessage.collectLatest{
            if (it.isNotEmpty()) Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }


    val sendDialogVisible = rememberSaveable() {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = (name ?: "") + "의 감사과", style = Typography.headlineMedium)
                },
                actions = {
                    IconButton(onClick = moveToProfile) {
                        Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null)
                    }
                },
                modifier = Modifier.padding(start = 4.dp, end = 10.dp)
            )
        },
//        bottomBar = {
//            BottomNavigationBar(navController = navController)
//        },
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(onClick = {sendDialogVisible.value = true},
                containerColor = seed,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier
                    .padding(bottom = 40.dp, end = 4.dp)
                    .size(70.dp)
            ) {
                Icon(imageVector = Icons.Default.MailOutline, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ){ padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(start = 24.dp, end = 14.dp),
        ) {
            ThankApoloTab(onTabClick = {viewModel.setMessageType(it)})
            // Todo : 메시지 리스트가 빈 거 일때 보여주는 화면 만들기
            messageList?.let {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { viewModel.getMessageData() }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "새로 고침")
                    }
                    IconButton(onClick = { hideVisible.value = !hideVisible.value }) {
                        Icon(imageVector = if (hideVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null)
                    }
                }
                if (it.isNotEmpty()) {

                    LazyColumn(
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        items(it) { item ->
                            if (!item.hidden || !item.hidden == !hideVisible.value) {
                                LetterCard(item, onDelete = { m ->
                                    viewModel.setCurrentMessage(m)
                                    removeDialogVisible.value = true
                                }, onHide = { m ->
                                    viewModel.setCurrentMessage(m)
                                    hideDialogVisible.value = true
                                })
                            }
                        }
                    }
                } else{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ){
                        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(painter = painterResource(id = R.drawable.empty_mail), contentDescription = null,
                                colorFilter = ColorFilter.tint(color = Color.Gray)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(text = "메일함이 비어있어요", style = Typography.labelMedium, color = Color.Gray)
                        }
                    }
                }
            }

            // 삭제 다이얼로그
            if (removeDialogVisible.value){
                ConfirmDialog(title = "삭제", content = "해당 메시지를 정말 삭제하시겠습니까?",
                    onConfirm = {
                        viewModel.deleteMessage()
                        removeDialogVisible.value = false
                    },
                    onDismiss = {removeDialogVisible.value = false}
                )
            }

            // 숨김 다이얼로그
            if (hideDialogVisible.value){
                ConfirmDialog(title = "숨김상태 변경", content = "해당 메시지의 숨김상태를 변경하시겠습니까?",
                    onConfirm = {
                        viewModel.changeVisibility()
                        hideDialogVisible.value = false
                    },
                    onDismiss = {hideDialogVisible.value = false}
                )
            }

        }

        if (sendDialogVisible.value){
            SendDialog(onDismiss = {sendDialogVisible.value = false})
        }
//        AnimatedVisibility(visible = sendDialogVisible.value, enter = slideInVertically(), exit = slideOutVertically()) {
//        }

    }
}
