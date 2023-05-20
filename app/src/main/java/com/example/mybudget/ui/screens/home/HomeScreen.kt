package com.example.mybudget.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybudget.R
import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.ui.component.CircularChart
import com.example.mybudget.ui.component.layout.ColumnScreenContainer

@Composable
internal fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val getNotesFlow = viewModel.getNotesFlow.collectAsState()
    var notes by remember { viewModel.notes }

    LaunchedEffect(Unit) {
        viewModel.getTransactionHistory(viewModel.auth.currentUser?.uid.orEmpty(),
            HomeViewModel.DAYS_BEFORE_TODAY_QUERY
        )
    }

    ColumnScreenContainer {
        Text(
            text = stringResource(
                id = R.string.home_screen_welcome_header,
                viewModel.auth.currentUser?.displayName ?: stringResource(
                    id = R.string.home_screen_welcome_username_placeholder
                )
            ),
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(id = R.color.text_color)
        )
        Spacer(modifier = Modifier.height(16.dp))
        CircularChart(
            sumValues = listOf(
                viewModel.getExpensesSum(),
                viewModel.getIncomeSum()
            ),
            values = listOf(
                viewModel.getExpensesPercentage(),
                viewModel.getIncomePercentage(),
            )
        )
        getNotesFlow.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }

                Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        notes = it.result
                    }
                }
            }
        }
    }
}