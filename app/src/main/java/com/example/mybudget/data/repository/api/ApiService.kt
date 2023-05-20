package com.example.mybudget.data.repository.api

import com.example.mybudget.BuildConfig
import com.example.mybudget.data.model.api.CurrencyRs
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ApiService {

    @GET("v1/latest")
    suspend fun getCurrencyRates(
        @Query("apikey") api_key: String = BuildConfig.API_KEY
    ): CurrencyRs
}