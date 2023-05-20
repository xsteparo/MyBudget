package com.example.mybudget.ui.component.topbar

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mybudget.R

@Composable
internal fun MenuIcon(
    imageVector: ImageVector = Icons.Default.Menu,
    @StringRes contentDescription: Int = R.string.menu_alt_text,
    padding: Dp = 16.dp
) {
    Row {
        Image(
            imageVector = imageVector,
            colorFilter = ColorFilter.tint(colorResource(id = R.color.text_color)),
            contentDescription = stringResource(id = contentDescription),
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(padding))
    }
}

@Composable
internal fun MenuTitle(title: String?) {
    Text(
        modifier = Modifier
            .padding(8.dp),
        text = title.orEmpty(),
        style = MaterialTheme.typography.labelLarge,
        color = colorResource(id = R.color.text_color)
    )
}