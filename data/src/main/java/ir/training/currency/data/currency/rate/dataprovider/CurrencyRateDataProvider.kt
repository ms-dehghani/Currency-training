package ir.training.currency.data.currency.rate.dataprovider

import ir.training.currency.domain.model.currency.rate.CurrencyRateItem

interface CurrencyRateDataProvider {
    suspend fun getUpdatedRates(): List<CurrencyRateItem>
}