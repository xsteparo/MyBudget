package com.example.mybudget.data.model.db

import com.google.firebase.Timestamp

internal data class Note(
    val userId: String?,
    val id: String,
    val title: String,
    val productType: String,
    val imageUrl: String?,
    val location: String?,
    val amount: Long,
    val createdAt: Timestamp?,
    val editedAt: Timestamp?,
)
