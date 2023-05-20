package com.example.mybudget.ui.screens.history

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

@HiltViewModel
internal class HistoryViewModel @Inject constructor(val repository: FirebaseFirestoreRepository, val auth: FirebaseAuthRepository) : ViewModel() {

    private val _getNotesFlow = MutableStateFlow<Resource<List<Note>>?>(null)
    internal val getNotesFlow: StateFlow<Resource<List<Note>>?> = _getNotesFlow

    internal fun getTransactionHistory(userId: String) = viewModelScope.launch {
        _getNotesFlow.value = Resource.Loading
        val result = repository.getNotes(userId)
        _getNotesFlow.value = result
    }
}