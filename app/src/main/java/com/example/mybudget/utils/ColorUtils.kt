package com.example.mybudget.utils

import androidx.compose.ui.res.colorResource
import com.example.mybudget.R
import com.example.mybudget.data.model.api.CurrencyRow
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

private val df = DecimalFormat("#,###.00")

internal fun getAmountColor(num: Long) =
    when {
        num > 0 -> R.color.income_color
        num < 0 -> R.color.expense_color
        else -> R.color.text_color
    }

internal fun Number?.formatNumber() = this?.let { df.format(it) }.orEmpty()

internal fun Number?.applyCurrencyRate(currencyRow: CurrencyRow) = this?.let { this.toFloat() * currencyRow.value!! } ?: 0.0

internal fun Date?.toFormattedDate() = this?.let { SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(it)}.orEmpty()