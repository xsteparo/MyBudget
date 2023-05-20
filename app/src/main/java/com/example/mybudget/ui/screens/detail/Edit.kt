package com.example.mybudget.ui.screens.detail

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mybudget.R
import com.example.mybudget.data.model.db.Note
import com.example.mybudget.data.model.db.ProductTypesEnum
import com.example.mybudget.ui.component.DropDownList
import com.example.mybudget.ui.component.layout.ColumnScreenContainer
import com.example.mybudget.ui.screens.addnote.AddNoteViewModel
import com.example.mybudget.utils.toNoteType
import okhttp3.internal.toLongOrDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Edit(note: Note, onEditClick: (Note) -> Unit) {
    var title by remember { mutableStateOf(note.title) }
    var selectedType by remember { mutableStateOf(note.productType.toNoteType()) }
    var location by remember { mutableStateOf(note.location.orEmpty()) }
    var isDropDownOpened by remember { mutableStateOf(false) }

    var amount by remember { mutableStateOf(note.amount.toString()) }

    val currentNote = Note(
        userId = note.userId,
        createdAt = note.createdAt,
        title = title,
        productType = selectedType.name,
        imageUrl = note.imageUrl,
        location = location,
        amount = amount.toLongOrDefault(0),
        editedAt = note.editedAt,
        id = note.id
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
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
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
                    .border(
                        BorderStroke(0.dp, Color.Transparent),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .clickable(
                        onClick = { isDropDownOpened = true }
                    )
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
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
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
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
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        Button(onClick = { onEditClick(currentNote) }) {
            Text(text = stringResource(id = R.string.edit))
        }
    }
}