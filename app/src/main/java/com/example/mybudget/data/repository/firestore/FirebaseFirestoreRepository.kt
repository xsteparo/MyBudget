package com.example.mybudget.data.repository.firestore

import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.data.model.db.Note
import com.google.firebase.firestore.CollectionReference

internal interface FirebaseFirestoreRepository {
    val notes: CollectionReference
    suspend fun addNewNote(note: Note): Resource<String>
    suspend fun getNotes(userId: String, daysBefore: Long? = null): Resource<List<Note>>
    suspend fun editNote(note: Note): Resource<String>
    suspend fun deleteNote(id: String): Resource<String>
}