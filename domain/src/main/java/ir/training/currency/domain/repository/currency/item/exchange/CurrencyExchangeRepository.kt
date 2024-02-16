package ir.training.currency.domain.repository.currency.item.exchange

import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem

interface CurrencyExchangeRepository {
    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Double,
        currencyList: List<CurrencyRateItem>
    ): ExchangeItem
}