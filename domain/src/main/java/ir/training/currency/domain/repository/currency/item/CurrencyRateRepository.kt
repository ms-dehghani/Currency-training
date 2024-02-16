package ir.training.currency.domain.repository.currency.item

import ir.training.currency.domain.model.CurrencyRateItem

interface CurrencyRateRepository {
    suspend fun getUpdatedRates(): List<CurrencyRateItem>
}