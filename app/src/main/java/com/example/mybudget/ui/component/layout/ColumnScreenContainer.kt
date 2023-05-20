package com.example.mybudget.ui.component.layout

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mybudget.R

@Composable
internal fun ColumnScreenContainer(
    @ColorRes backgroundColor: Int = R.color.bg_color,
    paddingTop: Dp = 40.dp,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable (ColumnScope.() -> Unit),
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = backgroundColor))
        .padding(top = paddingTop, start = 16.dp, end = 16.dp),
        horizontalAlignment = horizontalAlignment
    ) {
        content()
    }
}