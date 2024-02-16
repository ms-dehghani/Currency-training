package ir.training.currency.domain.repository.currency.item.rate

import ir.training.currency.domain.model.currency.rate.CurrencyRateItem

interface CurrencyRateRepository {
    suspend fun getUpdatedRates(): List<CurrencyRateItem>
}