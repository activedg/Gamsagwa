package com.handson.thankapolo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.handson.thankapolo.ui.theme.Typography

@Composable
fun ThankApoloTab(
){
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("전체", "감사함", "미안함")
    ScrollableTabRow(
        selectedTabIndex = tabIndex,
        indicator = { tabPositions ->
            Box{}
        },
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .fillMaxWidth(),
        divider = {},
        edgePadding = 12.dp
    ) {
        tabTitles.forEachIndexed { index, title ->
            val selected = tabIndex == index
            Tab(selected = selected,
                onClick = { tabIndex = index },
                text = { Text(
                    text = tabTitles[index],
                    style = Typography.labelMedium,
                    color = Color.Black,
                    )
                },
                modifier = if (selected) Modifier
                    .clip(RoundedCornerShape(100))
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                else Modifier
                    .clip(RoundedCornerShape(100))
                    .background(
                        color = Color.Transparent
                    ),
            )
        }
    }
}