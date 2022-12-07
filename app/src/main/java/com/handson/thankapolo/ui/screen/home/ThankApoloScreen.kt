package com.handson.thankapolo.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.thankapolo.component.LetterCard
import com.handson.thankapolo.component.ThankApoloTab
import com.handson.thankapolo.navigation.BottomNavigationBar

@Composable
fun ThankApoloScreen(
){
    val viewModel = hiltViewModel<ThankApoloViewModel>()
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThankApoloTab()
        Spacer(modifier = Modifier.height(30.dp))
//        Log.e("data", data.value.toString())
        LazyColumn{
            items(viewModel.letterData.value){ item ->
                LetterCard(item)
            }
        }
    }

}