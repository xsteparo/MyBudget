package com.example.mybudget.ui.screens.aboutus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mybudget.R
import com.example.mybudget.ui.component.layout.ColumnScreenContainer
import com.example.mybudget.ui.component.text.HeaderText
import com.example.mybudget.ui.component.text.ParagraphText

@Composable
internal fun AboutUsScreen() {
    ColumnScreenContainer(horizontalAlignment = Alignment.Start) {
        HeaderText(R.string.aboutus_screen_title)
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.aboutus), contentDescription = stringResource(
                    id = R.string.aboutus_screen_image_alt
                ),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(0.dp, 20.dp)
                    .size(120.dp)
                    .align(Alignment.Center)
            )
        }

        ParagraphText(textRes = R.string.aboutus_screen_text_1)
        Spacer(modifier = Modifier.height(10.dp))
        ParagraphText(textRes = R.string.aboutus_screen_text_2)
        Spacer(modifier = Modifier.height(10.dp))
        ParagraphText(textRes = R.string.aboutus_screen_text_3)
        Spacer(modifier = Modifier.height(10.dp))
        ParagraphText(textRes = R.string.aboutus_screen_text_4)
        Spacer(modifier = Modifier.height(30.dp))
    }
}