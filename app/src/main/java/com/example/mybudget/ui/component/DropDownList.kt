package com.example.mybudget.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mybudget.data.model.db.ProductTypesEnum

@Composable
fun DropDownList(
    requestToOpen: Boolean = false,
    list: List<ProductTypesEnum>,
    request: (Boolean) -> Unit,
    selectedString: (ProductTypesEnum) -> Unit
) {
    DropdownMenu(

        modifier = Modifier
            .fillMaxWidth(0.8f)
            .border(BorderStroke(0.dp, Color.Transparent), shape = RoundedCornerShape(12.dp)),
        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        list.forEach {
            DropdownMenuItem(
                text = { Text(
                    stringResource(id = it.text),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start))
                },
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    request(false)
                    selectedString(it)
                }
            )
        }
    }
}