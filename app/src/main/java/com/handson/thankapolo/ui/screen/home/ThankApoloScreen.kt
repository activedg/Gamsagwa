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
import com.handson.thankapolo.component.LetterCard
import com.handson.thankapolo.component.ThankApoloTab
import com.handson.thankapolo.navigation.BottomNavigationBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ThankApoloScreen(
){
    val viewModel = hiltViewModel<ThankApoloViewModel>()

    val context = LocalContext.current

    val messageList : MutableList<Message>? by viewModel.letterData.collectAsState()

    LaunchedEffect(Unit){
        viewModel.toastMessage.collectLatest{
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
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
                            LetterCard(item, onDelete = {
                                viewModel.deleteMessage(item)
                            },
                                onHide = {})
                        }
                    }
                }
            }

        }
    }

}
