package com.example.mybudget.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.mybudget.R
import com.example.mybudget.ui.screens.aboutus.AboutUsScreen
import com.example.mybudget.ui.screens.addnote.AddNoteScreen
import com.example.mybudget.ui.screens.auth.AuthViewModel
import com.example.mybudget.ui.screens.auth.LoginScreen
import com.example.mybudget.ui.screens.auth.SignUpScreen
import com.example.mybudget.ui.screens.contacts.ContactsScreen
import com.example.mybudget.ui.screens.currency.CurrencyScreen
import com.example.mybudget.ui.screens.detail.DetailScreen
import com.example.mybudget.ui.screens.history.HistoryScreen
import com.example.mybudget.ui.screens.home.HomeScreen
import com.example.mybudget.ui.screens.main.SharedViewModel

@Composable
internal fun Navigation(
    authViewModel: AuthViewModel,
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(navController, startDestination = Screen.Login.route, modifier) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.AddNote.route) {
            AddNoteScreen(
                authViewModel = authViewModel,
                navController = navController,
            )
        }
        composable(Screen.History.route) {
            HistoryScreen(
                sharedViewModel = sharedViewModel,
                navController = navController,
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = authViewModel,
                navController = navController,
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                viewModel = authViewModel,
                navController = navController,
            )
        }
        composable(Screen.Contacts.route) {
            ContactsScreen(
                navController = navController,
            )
        }
        composable(Screen.Currency.route) {
            CurrencyScreen(
                sharedViewModel = sharedViewModel,
                navController = navController,
            )
        }
        composable(Screen.AboutUs.route) {
            AboutUsScreen()
        }

        composable(
            Screen.NoteDetail.route.plus(Screen.NoteDetail.objectPath),
            arguments = listOf(navArgument(Screen.NoteDetail.objectName) {
                type = NavType.StringType
            })
        ) {
            label = stringResource(R.string.details_screen_title)
            val noteId = it.arguments?.getString(Screen.NoteDetail.objectName)
            noteId?.let {
                DetailScreen(
                    sharedViewModel = sharedViewModel,
                    navController = navController,
                    noteId = noteId
                )
            }
        }
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.AddNote.route -> stringResource(id = R.string.add_screen_title)
        Screen.History.route -> stringResource(id = R.string.history_screen_title)
        Screen.Home.route -> stringResource(id = R.string.app_name)
        else -> {
            ""
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}