package com.example.mybudget.ui.screens.detail

import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mybudget.R
import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.navigation.Screen
import com.example.mybudget.ui.component.layout.ColumnScreenContainer
import com.example.mybudget.ui.component.text.HeaderText
import com.example.mybudget.ui.component.text.ParagraphText
import com.example.mybudget.ui.component.text.ParagraphTitleText
import com.example.mybudget.ui.screens.main.SharedViewModel
import com.example.mybudget.utils.applyCurrencyRate
import com.example.mybudget.utils.formatNumber
import com.example.mybudget.utils.getAmountColor
import com.example.mybudget.utils.toFormattedDate

@Composable
internal fun DetailScreen(
    sharedViewModel: SharedViewModel,
    navController: NavController,
    noteId: String
) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val deleteNoteFlow = viewModel.deleteNoteFlow.collectAsState()
    val editNoteFlow = viewModel.editNoteFlow.collectAsState()
    var isEditMode by remember { mutableStateOf(false) }
    var note by remember { mutableStateOf(sharedViewModel.historyNotes?.firstOrNull { it.id == noteId }) }
    if (!isEditMode) {
        ColumnScreenContainer {
            HeaderText(textRes = R.string.details_screen_header_text)
            Spacer(modifier = Modifier.height(40.dp))

            note?.let {
                DetailsRow(
                    R.string.details_screen_date_row_title,
                    it.createdAt?.toDate().toFormattedDate()
                )
                Spacer(modifier = Modifier.height(16.dp))
                DetailsRow(R.string.details_screen_title_row_title, it.title)
                Spacer(modifier = Modifier.height(16.dp))
                DetailsRow(R.string.details_screen_type_row_title, it.productType)
                Spacer(modifier = Modifier.height(16.dp))
                DetailsRow(R.string.details_screen_location_row_title, it.location.orEmpty())
                Spacer(modifier = Modifier.height(16.dp))
                DetailsRow(
                    R.string.details_screen_amount_row_title,
                    sharedViewModel.selectedCurrency?.let {curr ->
                        it.amount.applyCurrencyRate(curr).formatNumber().plus(" ${curr.name}")
                    } ?: it.amount.formatNumber().plus(" USD"),
                    getAmountColor(it.amount)
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (!it.imageUrl.isNullOrEmpty()) {
                    AsyncImage(
                        model = it.imageUrl,
                        contentDescription = stringResource(id = R.string.details_screen_product_image_alt),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = { isEditMode = true },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.orange_color))
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = stringResource(id = R.string.edit)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        ParagraphText(textRes = R.string.edit)
                    }
                    Button(
                        onClick = { viewModel.deleteNote(it.id) },
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red_color))
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = stringResource(id = R.string.delete)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        ParagraphText(textRes = R.string.delete)
                    }
                }
            } ?: ParagraphText(textRes = R.string.no_data)

            deleteNoteFlow.value?.let {
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
                            navController.navigate(Screen.History.route) {
                                popUpTo(Screen.NoteDetail.route) { inclusive = true }
                            }
                        }
                    }
                }
            }
        }
    } else {
        note?.let {
            Column(modifier = Modifier.fillMaxSize()) {
                Edit(note = it, onEditClick = { updatedNote ->
                    note = updatedNote
                    viewModel.editNote(updatedNote)
                    isEditMode = false
                })
                editNoteFlow.value?.let {
                    when (it) {
                        is Resource.Failure -> {
                            val context = LocalContext.current
                            Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                        }

                        Resource.Loading -> {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }

                        is Resource.Success -> {
                            Toast.makeText(
                                LocalContext.current,
                                stringResource(id = R.string.details_screen_success_edit_toast),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        }
    }
}

@Composable
private fun DetailsRow(@StringRes title: Int, value: String, @ColorRes valueColor: Int? = null) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        ParagraphTitleText(textRes = title)
        valueColor?.let { ParagraphTitleText(text = value, color = valueColor) }
            ?: ParagraphTitleText(text = value)
    }
}