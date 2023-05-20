package com.example.mybudget.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.data.model.api.CurrencyData
import com.example.mybudget.data.model.api.CurrencyRow
import com.example.mybudget.data.model.api.CurrencyRs
import com.example.mybudget.data.model.db.Note
import com.example.mybudget.data.repository.api.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SharedViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    internal var historyNotes: List<Note>? = null
    internal var currencyData: CurrencyData? = null
    internal var selectedCurrency: CurrencyRow? = null

    private val _getCurrencyFlow = MutableStateFlow<Resource<CurrencyRs>?>(null)
    internal val getCurrencyFlow: StateFlow<Resource<CurrencyRs>?> = _getCurrencyFlow

    internal fun getCurrencyData() = viewModelScope.launch {
        _getCurrencyFlow.value = Resource.Loading
        val result = apiRepository.getCurrencyRates()
        _getCurrencyFlow.value = result
    }
}