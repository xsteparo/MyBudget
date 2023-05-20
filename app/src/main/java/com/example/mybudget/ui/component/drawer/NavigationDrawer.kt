package com.example.mybudget.ui.component.drawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mybudget.R
import com.example.mybudget.navigation.Screen
import com.example.mybudget.ui.screens.auth.AuthViewModel
import com.example.mybudget.ui.theme.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NavigationDrawer(
    viewModel: AuthViewModel?,
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState,
) {
    val items = listOf(Screen.AboutUs, Screen.Currency, Screen.Contacts)
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalDrawerSheet {
        Spacer(Modifier.height(12.dp))
        items.forEach { item ->
            NavigationDrawerItem(
                icon = { item.navIcon },
                label = { Text(stringResource(id = item.title)) },
                selected = item == selectedItem.value,
                onClick = {
                    scope.launch { drawerState.close() }
                    selectedItem.value = item
                    navController.navigate(item.route)
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = MaterialTheme.spacing.extraLarge),
            onClick = {
                scope.launch { drawerState.close() }
                viewModel?.logout()
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
        ) {
            Text(text = stringResource(id = R.string.drawer_logout_button))
        }

    }
//    Column(modifier = Modifier.fillMaxSize()) {
//
//    Column(modifier = Modifier.fillMaxHeight(1f)) {
//        Text(text = stringResource(id = R.string.drawer_about_us_title))
//        Spacer(modifier = Modifier.height(5.dp))
//        Text(text = stringResource(id = R.string.drawer_about_us_title))
//        Spacer(modifier = Modifier.height(5.dp))
//        Text(text = stringResource(id = R.string.drawer_about_us_title))
//        Spacer(modifier = Modifier.height(5.dp))
//        Text(text = stringResource(id = R.string.drawer_about_us_title))
//    }
//        Button(
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .padding(top = MaterialTheme.spacing.extraLarge),
//            onClick = {
//            viewModel?.logout()
//            navController.navigate(Screen.Login.route) {
//                popUpTo(Screen.Home.route) { inclusive = true }
//            }
//        },
//        ) {
//            Text(text = stringResource(id = R.string.drawer_logout_button))
//        }
//    }
}