package com.example.mybudget.ui.component.topbar

import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.mybudget.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBarWithBackNav(title: String?, openDrawer: () -> Unit, pressOnBack: (() -> Unit), @ColorRes color: Int = R.color.bg_color) {
    TopAppBar(
        title = { MenuTitle(title = title) },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = color)),
        actions = {
            IconButton(
                onClick = { openDrawer() }
            ) { MenuIcon() }
        },

        navigationIcon =
        {
                Row {
                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        imageVector = Icons.Filled.ArrowBack,
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color)),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable {
                                pressOnBack()
                            }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    MenuTitle(title = title)
                }

        },
    )
}