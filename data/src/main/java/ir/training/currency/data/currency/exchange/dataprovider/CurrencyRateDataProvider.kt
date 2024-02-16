package ir.training.currency.data.currency.exchange.dataprovider

import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem

interface CurrencyExchangeDataProvider {
    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Double,
        currencyList: List<CurrencyRateItem>
    ): ExchangeItem
}