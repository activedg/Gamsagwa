package com.handson.thankapolo.ui.screen.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.domain.data.home.Message
import com.handson.thankapolo.component.ConfirmDialog
import com.handson.thankapolo.component.LetterCard
import com.handson.thankapolo.component.ThankApoloTab
import com.handson.thankapolo.navigation.BottomNavigationBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ThankApoloScreen(
){
    val viewModel = hiltViewModel<ThankApoloViewModel>()

    val context = LocalContext.current

    // 삭제 다이얼로그
    val removeDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val hideDialogVisible = rememberSaveable {
        mutableStateOf(false)
    }

    val messageList : MutableList<Message>? by viewModel.letterData.collectAsState()

    LaunchedEffect(Unit){
        viewModel.toastMessage.collectLatest{
            if (it.isNotEmpty()) Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
    ) {
        ThankApoloTab(onTabClick = {viewModel.setMessageType(it)})
        Spacer(modifier = Modifier.height(20.dp))
        // Todo : 메시지 리스트가 빈 거 일때 보여주는 화면 만들기
        messageList?.let {
            if (it.isEmpty()){

            } else{
                LazyColumn{
                    items(it){ item ->
                        if (!item.hidden) {
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
            ConfirmDialog(title = "숨김", content = "해당 메시지를 숨기겠습니까?",
                onConfirm = {
                    viewModel.changeVisibility()
                    hideDialogVisible.value = false
                },
                onDismiss = {hideDialogVisible.value = false}
            )
        }

    }

}
