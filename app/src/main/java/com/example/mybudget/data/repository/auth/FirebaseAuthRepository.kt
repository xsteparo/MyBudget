package com.example.mybudget.data.repository.auth

import com.example.mybudget.data.datasource.remote.Resource
import com.google.firebase.auth.FirebaseUser

internal interface FirebaseAuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}