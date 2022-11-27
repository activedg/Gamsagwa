package com.handson.thankapolo.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.thankapolo.component.LetterCard
import com.handson.thankapolo.component.ThankApoloTab
import com.handson.thankapolo.navigation.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThankApoloScreen(
){
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThankApoloTab()
        Spacer(modifier = Modifier.height(30.dp))
        LetterCard()
    }

}