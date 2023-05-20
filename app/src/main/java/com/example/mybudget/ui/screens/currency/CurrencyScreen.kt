package com.example.mybudget.ui.screens.currency

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mybudget.R
import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.data.model.api.CurrencyData
import com.example.mybudget.data.model.api.CurrencyRow
import com.example.mybudget.ui.component.layout.ColumnScreenContainer
import com.example.mybudget.ui.component.text.HeaderText
import com.example.mybudget.ui.component.text.ParagraphText
import com.example.mybudget.ui.screens.main.SharedViewModel
import com.example.mybudget.utils.toListOfRows

@Composable
internal fun CurrencyScreen(sharedViewModel: SharedViewModel, navController: NavController) {
    val getCurrencyFlow = sharedViewModel.getCurrencyFlow.collectAsState()
    var currentData by remember { mutableStateOf<CurrencyData?>(null) }
    val context = LocalContext.current
    ColumnScreenContainer {
        LaunchedEffect(Unit) {
            sharedViewModel.getCurrencyData()
        }
        HeaderText(textRes = R.string.currency_screen_header)
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn(modifier = Modifier.padding(20.dp, 0.dp)) {
            currentData?.let {
                currentData.toListOfRows().forEach {
                    item {
                        if (it.value != null) {
                            CurrencyRow(it){ currency ->
                                Toast.makeText(context, "Currency selected", Toast.LENGTH_SHORT).show()
                                sharedViewModel.selectedCurrency = currency
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
            }
        }

        getCurrencyFlow.value?.let {
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
                        sharedViewModel.currencyData = it.result.data
                        currentData = it.result.data
                    }
                }
            }
        }
    }

}

@Composable
private fun CurrencyRow(currencyData: CurrencyRow, onClick: (CurrencyRow) -> Unit){
    val name = currencyData.name
    val value = currencyData.value.toString()

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick(currencyData)
        }) {
         ParagraphText(text = name, color = R.color.yellow_color)
         ParagraphText(text = value)
    }
}