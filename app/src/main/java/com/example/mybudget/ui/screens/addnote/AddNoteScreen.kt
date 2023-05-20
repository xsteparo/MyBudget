package com.example.mybudget.ui.screens.addnote

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mybudget.R
import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.data.model.db.Note
import com.example.mybudget.data.model.db.ProductTypesEnum
import com.example.mybudget.navigation.Screen
import com.example.mybudget.ui.component.DropDownList
import com.example.mybudget.ui.component.layout.ColumnScreenContainer
import com.example.mybudget.ui.screens.auth.AuthViewModel
import okhttp3.internal.toLongOrDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddNoteScreen(authViewModel: AuthViewModel, navController: NavController) {
    var title by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(ProductTypesEnum.SHOP) }
    var location by remember { mutableStateOf("") }
    var isDropDownOpened by remember { mutableStateOf(false) }
    val viewModel = hiltViewModel<AddNoteViewModel>()
    val addNewNoteFlow = viewModel.addNewNoteFlow.collectAsState()

    var amount by remember { mutableStateOf("") }

    val currentNote = Note(
        userId = authViewModel.currentUser?.uid.orEmpty(),
        createdAt = null,
        title = title,
        productType = selectedType.name,
        imageUrl = "",
        location = location,
        amount = amount.toLongOrDefault(0),
        editedAt = null,
        id = ""
    )

    ColumnScreenContainer {
        TextField(
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            value = title,
            onValueChange = {
                title = it
            },
            label = {
                Text(text = stringResource(id = R.string.add_screen_input_title_hint))
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        Box {
            Column {
                TextField(
                    shape = RoundedCornerShape(24.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    value = stringResource(id = selectedType.text),
                    enabled = true,
                    onValueChange = { },
                    label = { Text(text = stringResource(id = R.string.add_screen_input_type_hint)) },
                )
                DropDownList(
                    list = ProductTypesEnum.values().toList(),
                    request = { isDropDownOpened = it },
                    requestToOpen = isDropDownOpened,
                    selectedString = { selectedType = it }
                )
            }
            Spacer(
                modifier = Modifier

                    .matchParentSize()
                    .background(Color.Transparent)
                    .border(BorderStroke(0.dp, Color.Transparent), shape = RoundedCornerShape(24.dp))
                    .clickable(
                        onClick = { isDropDownOpened = true }
                    )
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        TextField(
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            value = location,
            onValueChange = {
                location = it
            },
            label = {
                Text(text = stringResource(id = R.string.add_screen_input_location_hint))
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        TextField(
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            value = amount,
            onValueChange = { amount = it },
            label = {
                Text(text = stringResource(id = R.string.add_screen_input_amount_hint))
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        Button(onClick = { viewModel.addNewNote(currentNote) }) {
            Text(text = stringResource(id = R.string.add_screen_button_add))
        }

        addNewNoteFlow.value?.let {
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
                            popUpTo(Screen.AddNote.route) { inclusive = true }
                        }
                    }
                }
            }
        }
    }
}
