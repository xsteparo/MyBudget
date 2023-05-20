package com.example.mybudget.ui.screens.history

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mybudget.R
import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.data.model.db.Note
import com.example.mybudget.navigation.Screen
import com.example.mybudget.ui.component.layout.ColumnScreenContainer
import com.example.mybudget.ui.component.topbar.MenuIcon
import com.example.mybudget.ui.screens.main.SharedViewModel
import com.example.mybudget.utils.applyCurrencyRate
import com.example.mybudget.utils.formatNumber
import com.example.mybudget.utils.getAmountColor
import com.example.mybudget.utils.toNoteType

@Composable
internal fun HistoryScreen(sharedViewModel: SharedViewModel, navController: NavController) {
    val viewModel = hiltViewModel<HistoryViewModel>()
    val getNotesFlow = viewModel.getNotesFlow.collectAsState()
    var notes by remember { mutableStateOf(emptyList<Note>()) }
    LaunchedEffect(Unit) {
        viewModel.getTransactionHistory(viewModel.auth.currentUser?.uid.orEmpty())
    }
    ColumnScreenContainer {

        Divider(thickness = 1.dp, color = colorResource(id = R.color.text_color))
        LazyColumn {
            notes.forEach { note ->
                item {
                    HistoryRow(note, sharedViewModel, navController)
                }
            }
        }

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
                        sharedViewModel.historyNotes = it.result
                        notes = it.result
                    }
                }
            }
        }
    }
}

@Composable
private fun HistoryRow(note: Note, sharedViewModel: SharedViewModel, navController: NavController) {
    val amount = (if (note.amount >= 0) "+" else "").plus(sharedViewModel.selectedCurrency?.let {curr ->
        note.amount.applyCurrencyRate(curr).formatNumber().plus(" ${curr.name}")
    } ?: note.amount.formatNumber().plus(" USD"))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.NoteDetail.route.plus("/${note.id}"))
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            MenuIcon(imageVector = note.productType.toNoteType().imageVector)
            Text(text = note.title, color = colorResource(id = R.color.text_color))
        }
        Text(
            text = amount,
            color = colorResource(id = getAmountColor(note.amount))
        )
    }
    Divider(thickness = 1.dp, color = colorResource(id = R.color.text_color))

}