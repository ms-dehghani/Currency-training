package ir.training.currency.data

import ir.training.currency.domain.model.CurrencyRateItem

interface ServiceRepository {

    suspend fun getUpdatedRates(): List<CurrencyRateItem>

}