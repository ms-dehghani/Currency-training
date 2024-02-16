package ir.training.currency.data.service.repository.api

import ir.training.currency.data.service.dto.api.currency.rate.CurrencyRateResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/tasks/api/currency-exchange-rates")
    suspend fun getUpdatedRates(): CurrencyRateResponse?

}