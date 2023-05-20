package com.example.mybudget.ui.screens.detail

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
internal class DetailViewModel @Inject constructor(val repository: FirebaseFirestoreRepository) : ViewModel() {

    private val _deleteNoteFlow = MutableStateFlow<Resource<String>?>(null)
    internal val deleteNoteFlow: StateFlow<Resource<String>?> = _deleteNoteFlow

    private val _editNoteFlow = MutableStateFlow<Resource<String>?>(null)
    internal val editNoteFlow: StateFlow<Resource<String>?> = _editNoteFlow

    internal fun editNote(updatedNote: Note) = viewModelScope.launch {
        _editNoteFlow.value = Resource.Loading
        val result = repository.editNote(updatedNote)
        _editNoteFlow.value = result
    }

    internal fun deleteNote(id: String) = viewModelScope.launch {
        _deleteNoteFlow.value = Resource.Loading
        val result = repository.deleteNote(id)
        _deleteNoteFlow.value = result
    }
}