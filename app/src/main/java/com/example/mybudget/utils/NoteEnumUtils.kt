package com.example.mybudget.utils

import com.example.mybudget.data.model.api.CurrencyData
import com.example.mybudget.data.model.api.CurrencyRow
import com.example.mybudget.data.model.db.ProductTypesEnum

internal fun String?.toNoteType(): ProductTypesEnum = ProductTypesEnum.values().firstOrNull() { this == it.name } ?: ProductTypesEnum.SHOP

internal fun CurrencyData?.toListOfRows(): List<CurrencyRow> = this?.run {
    listOf(
        CurrencyRow(::AUD.name, AUD),
        CurrencyRow(::BGN.name, BGN),
        CurrencyRow(::BRL.name, BRL),
        CurrencyRow(::CAD.name, CAD),
        CurrencyRow(::CHF.name, CHF),
        CurrencyRow(::CNY.name, CNY),
        CurrencyRow(::CZK.name, CZK),
        CurrencyRow(::DKK.name, DKK),
        CurrencyRow(::EUR.name, EUR),
        CurrencyRow(::GBP.name, GBP),
        CurrencyRow(::HKD.name, HKD),
        CurrencyRow(::HRK.name, HRK),
        CurrencyRow(::HUF.name, HUF),
        CurrencyRow(::IDR.name, IDR),
        CurrencyRow(::ILS.name, ILS),
        CurrencyRow(::INR.name, INR),
        CurrencyRow(::ISK.name, ISK),
        CurrencyRow(::JPY.name, JPY),
        CurrencyRow(::KRW.name, KRW),
        CurrencyRow(::MXN.name, MXN),
        CurrencyRow(::MYR.name, MYR),
        CurrencyRow(::NOK.name, NOK),
        CurrencyRow(::NZD.name, NZD),
        CurrencyRow(::PHP.name, PHP),
        CurrencyRow(::PLN.name, PLN),
        CurrencyRow(::RON.name, RON),
        CurrencyRow(::RUB.name, RUB),
        CurrencyRow(::SEK.name, SEK),
        CurrencyRow(::SGD.name, SGD),
        CurrencyRow(::THB.name, THB),
        CurrencyRow(::TRY.name, TRY),
        CurrencyRow(::USD.name, USD),
    )
}.orEmpty()