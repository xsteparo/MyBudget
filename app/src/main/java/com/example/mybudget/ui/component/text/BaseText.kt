package com.example.mybudget.ui.component.text

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.mybudget.R

@Composable
internal fun HeaderText(@StringRes textRes: Int) {
    Text(
        text = stringResource(id = textRes),
        color = colorResource(id = R.color.text_color),
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Start
    )
}

@Composable
internal fun ParagraphText(@StringRes textRes: Int, @ColorRes color: Int = R.color.text_color) {
    Text(
        text = stringResource(id = textRes),
        color = colorResource(id = color),
        textAlign = TextAlign.Start
    )
}

@Composable
internal fun ParagraphText(text: String, @ColorRes color: Int = R.color.text_color) {
    Text(
        text = text,
        color = colorResource(id = color),
        textAlign = TextAlign.Start
    )
}

@Composable
internal fun ParagraphTitleText(@StringRes textRes: Int, @ColorRes color: Int = R.color.text_color) {
    Text(
        text = stringResource(id = textRes),
        color = colorResource(id = color),
        style = MaterialTheme.typography.titleSmall,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
internal fun ParagraphTitleText(text: String, @ColorRes color: Int = R.color.text_color, ) {
    Text(
        text = text,
        color = colorResource(id = color),
        style = MaterialTheme.typography.titleSmall,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold,
    )
}