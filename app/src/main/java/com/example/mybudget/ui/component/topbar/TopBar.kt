package com.example.mybudget.ui.component.topbar

import androidx.annotation.ColorRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.mybudget.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(title: String?, openDrawer: () -> Unit, @ColorRes color: Int = R.color.bg_color) {
    TopAppBar(
        title = {
            MenuTitle(title = title)
        },
        actions = {
            IconButton(
                onClick = { openDrawer() }
            ) { MenuIcon() }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = color)),
    )
}