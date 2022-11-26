package com.handson.thankapolo.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.handson.thankapolo.navigation.BottomNavigationBar
import com.handson.thankapolo.navigation.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    name: String
){
    val colorScheme = MaterialTheme.colorScheme

    val systemUiController = rememberSystemUiController()

    val navController = rememberNavController()
    
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorScheme.background,
            darkIcons = true
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "${name}의 감사과")
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(imageVector = Icons.Filled.Notifications, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null)
                    }
                },
                modifier = Modifier.padding(start = 24.dp, end = 12.dp)
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = MaterialTheme.colorScheme.background
    ){ padding ->
        NavHost(navController = navController, startDestination = NavigationItem.Home.route, Modifier.padding(padding)){
            composable(NavigationItem.Home.route){ ThankApoloScreen() }
            composable(NavigationItem.Look.route){}
        }
    }
}