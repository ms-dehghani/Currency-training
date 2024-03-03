package ir.training.currency.data.currency.exchange.dataprovider

import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.domain.model.exchange.FakeExchangeItem

interface CurrencyExchangeDataProvider {
    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Double,
        currencyList: List<CurrencyRateItem>
    ): ExchangeItem

    suspend fun convertFakeCurrency(
        from: String,
        to: String,
        amount: Double,
        currencyList: List<CurrencyRateItem>
    ): FakeExchangeItem
}