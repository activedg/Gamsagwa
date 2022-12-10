package com.handson.thankapolo.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.thankapolo.navigation.BottomNavigationBar
import com.handson.thankapolo.navigation.NavigationItem
import com.handson.thankapolo.ui.screen.home.SendDialog
import com.handson.thankapolo.ui.screen.home.ThankApoloScreen
import com.handson.thankapolo.ui.theme.Typography
import com.handson.thankapolo.ui.theme.seed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    name: String,
    moveToProfile: () -> Unit
){
    val navController = rememberNavController()

    val sendDialogVisible = rememberSaveable() {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "${name}의 감사과", style = Typography.headlineMedium)
                },
                actions = {
                    IconButton(onClick = moveToProfile) {
                        Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null)
                    }
                },
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(onClick = {sendDialogVisible.value = true},
                containerColor = seed,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.size(70.dp)
            ) {
                Icon(imageVector = Icons.Default.MailOutline, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ){ padding ->
        NavHost(navController = navController, startDestination = NavigationItem.Home.route, Modifier.padding(padding)){
            composable(NavigationItem.Home.route){ ThankApoloScreen() }
            composable(NavigationItem.Look.route){}
        }

        AnimatedVisibility(visible = sendDialogVisible.value, enter = expandIn(), exit = shrinkOut()) {
            SendDialog(onDismiss = {sendDialogVisible.value = false})
        }

    }
}