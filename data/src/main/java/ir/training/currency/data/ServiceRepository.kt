package ir.training.currency.data

import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem

interface ServiceRepository {

    suspend fun getUpdatedRates(): List<CurrencyRateItem>

    suspend fun getCurrencyLogList(): List<CurrencyLogItem>

    suspend fun addLog(log: CurrencyLogItem): Boolean

    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Double,
        currencyList: List<CurrencyRateItem>
    ): ExchangeItem
}