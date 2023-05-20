package com.example.mybudget.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.data.model.db.Note
import com.example.mybudget.data.repository.auth.FirebaseAuthRepository
import com.example.mybudget.data.repository.firestore.FirebaseFirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
internal class HomeViewModel @Inject constructor(val repository: FirebaseFirestoreRepository, val auth: FirebaseAuthRepository) : ViewModel() {

    private val _getNotesFlow = MutableStateFlow<Resource<List<Note>>?>(null)
    internal val getNotesFlow: StateFlow<Resource<List<Note>>?> = _getNotesFlow

    internal var notes = mutableStateOf(emptyList<Note>())

    internal fun getTransactionHistory(userId: String, daysBefore: Long) = viewModelScope.launch {
        _getNotesFlow.value = Resource.Loading
        val result = repository.getNotes(userId, daysBefore)
        _getNotesFlow.value = result
    }

    private fun getTotalAbsSum() = notes.value.sumOf { abs(it.amount) }

    internal fun getExpensesSum() = notes.value.filter { it.amount < 0 }.sumOf { it.amount }

    private fun getExpensesAbsSum() = notes.value.filter { it.amount < 0 }.sumOf { abs(it.amount) }

    internal fun getIncomeSum() = notes.value.filter { it.amount >= 0 }.sumOf { it.amount }

    private fun getIncomeAbsSum() = notes.value.filter { it.amount >= 0 }.sumOf { abs(it.amount) }

    internal fun getIncomePercentage() = if (getTotalAbsSum() != 0L) getIncomeAbsSum().toFloat() / getTotalAbsSum().toFloat() * 100f else 0f
    internal fun getExpensesPercentage() = if (getTotalAbsSum() != 0L) getExpensesAbsSum().toFloat() / getTotalAbsSum().toFloat() * 100f else 0f

    internal companion object {
        const val DAYS_BEFORE_TODAY_QUERY = 30L
    }
}