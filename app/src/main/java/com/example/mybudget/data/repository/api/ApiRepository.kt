package com.example.mybudget.data.repository.api

import com.example.mybudget.data.datasource.remote.Resource
import com.example.mybudget.data.model.api.CurrencyRs
import javax.inject.Inject

internal class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCurrencyRates(): Resource<CurrencyRs> {
        return try {
            val result = apiService.getCurrencyRates()
            Resource.Success(result = result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}