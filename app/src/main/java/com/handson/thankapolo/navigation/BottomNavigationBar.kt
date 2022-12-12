package com.handson.thankapolo.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        NavigationItem.Home,
    )
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.04f)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any{it.route == item.route} == true,
                icon = { Icon(imageVector = item.icon, contentDescription = null)},
                label = { Text(text = item.title, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 4.dp))},
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}