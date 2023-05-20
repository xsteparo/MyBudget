package com.example.mybudget.ui.screens.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.data.model.db.Note
import com.example.mybudget.data.repository.firestore.FirebaseFirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AddNoteViewModel @Inject constructor(val repository: FirebaseFirestoreRepository) : ViewModel() {
    private val _addNewNoteFlow = MutableStateFlow<Resource<String>?>(null)
    val addNewNoteFlow: StateFlow<Resource<String>?> = _addNewNoteFlow

    internal fun addNewNote(note: Note) = viewModelScope.launch {
        _addNewNoteFlow.value = Resource.Loading
        val result = repository.addNewNote(note)
        _addNewNoteFlow.value = result
    }
}