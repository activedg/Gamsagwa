package com.handson.thankapolo.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ArrowImage(
    @DrawableRes painterResource: Int,
    modifier: Modifier = Modifier,
    xOffset: Float
){
    Image(
        painter = painterResource(id = painterResource),
        contentDescription = null,
        modifier = Modifier.offset(xOffset.dp),
        contentScale = ContentScale.Fit
    )
}