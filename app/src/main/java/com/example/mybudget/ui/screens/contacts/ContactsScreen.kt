package com.example.mybudget.ui.screens.contacts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
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
import com.example.mybudget.ui.component.text.ParagraphTitleText

@Composable
internal fun ContactsScreen(navController: NavController) {
    ColumnScreenContainer(horizontalAlignment = Alignment.Start) {
        HeaderText(R.string.contacts_screen_title)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                ParagraphTitleText(textRes = R.string.contacts_screen_dev1_title)
                ParagraphText(textRes = R.string.contacts_screen_dev1_name)
                ParagraphText(textRes = R.string.contacts_screen_dev1_phone)
            }
            Image(
                painter = painterResource(id = R.drawable.dev1),
                contentDescription = stringResource(
                    id = R.string.contacts_screen_dev1_image_alt
                ),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column() {
                ParagraphTitleText(textRes = R.string.contacts_screen_dev2_title)
                ParagraphText(textRes = R.string.contacts_screen_dev2_name)
                ParagraphText(textRes = R.string.contacts_screen_dev2_phone)
            }
            Image(
                painter = painterResource(id = R.drawable.dev2),
                contentDescription = stringResource(
                    id = R.string.contacts_screen_dev2_image_alt
                ),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp)
            )
        }
    }
}