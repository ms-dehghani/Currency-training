package ir.training.currency.data

import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.domain.model.exchange.FakeExchangeItem
import ir.training.currency.domain.model.wallet.WalletItem

interface ServiceRepository {

    suspend fun getWallet(): WalletItem
    suspend fun getUpdatedRates(): List<CurrencyRateItem>

    suspend fun getCurrencyLogList(): List<CurrencyLogItem>

    suspend fun addLog(log: CurrencyLogItem): Boolean

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