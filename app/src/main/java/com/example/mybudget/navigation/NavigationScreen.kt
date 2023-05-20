package com.example.mybudget.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mybudget.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int = R.string.app_name,
    val navIcon: (@Composable () -> Unit) = {
        Icon(
            Icons.Filled.Home, contentDescription = "home"
        )
    },
    val objectName: String = "",
    val objectPath: String = "",
) {
    object Login : Screen("login_screen")
    object SignUp : Screen("signup_screen")
    object Home : Screen("home_screen")
    object AddNote : Screen("add_note_screen")
    object History : Screen("history_screen")
    object NoteDetail :
        Screen("note_detail_screen", objectName = "noteItem", objectPath = "/{noteItem}")

    object Contacts : Screen("contacts_screen", title = R.string.contacts_screen_title, navIcon = {
        Icon(
            Icons.Filled.Email,
            contentDescription = stringResource(id = R.string.drawer_contacts_icon_alt),
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })

    object AboutUs : Screen("aboutus_screen", title = R.string.aboutus_screen_title, navIcon = {
        Icon(
            Icons.Filled.Info,
            contentDescription = stringResource(id = R.string.drawer_about_us_icon_alt),
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })

    object Currency : Screen("currency_screen", title = R.string.currency_screen_title, navIcon = {
        Icon(
            Icons.Filled.AccountBox,
            contentDescription = stringResource(id = R.string.drawer_currency_icon_alt),
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })


    // Bottom Navigation
    object HomeNav : Screen("home_screen", title = R.string.home_screen_title, navIcon = {
        Icon(
            Icons.Filled.Home,
            contentDescription = stringResource(id = R.string.home_icon_alt_text),
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })

    object AddNav : Screen("add_note_screen", title = R.string.add_screen_title, navIcon = {
        Icon(
            Icons.Filled.AddCircle,
            contentDescription = stringResource(id = R.string.add_note_icon_alt_text),
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })

    object HistoryNav : Screen("history_screen", title = R.string.history_screen_title, navIcon = {
        Icon(
            Icons.Filled.List,
            contentDescription = stringResource(id = R.string.history_icon_alt_text),
            modifier = Modifier
                .padding(end = 16.dp)
                .offset(x = 10.dp)
        )
    })
}