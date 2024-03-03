package ir.training.currency.data.currency.exchange.repository

import ir.training.currency.data.currency.exchange.dataprovider.CurrencyExchangeDataProvider
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.domain.model.exchange.FakeExchangeItem
import ir.training.currency.domain.repository.currency.item.exchange.CurrencyExchangeRepository
import javax.inject.Inject

internal class CurrencyExchangeRepositoryImpl @Inject constructor(private val dataProvider: CurrencyExchangeDataProvider) :
    CurrencyExchangeRepository {
    override suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Double,
        currencyList: List<CurrencyRateItem>
    ): ExchangeItem {
        return dataProvider.convertCurrency(
            from = from,
            to = to,
            amount = amount,
            currencyList = currencyList
        )
    }

    override suspend fun convertFakeCurrency(
        from: String,
        to: String,
        amount: Double,
        currencyList: List<CurrencyRateItem>
    ): FakeExchangeItem {
        return dataProvider.convertFakeCurrency(
            from = from,
            to = to,
            amount = amount,
            currencyList = currencyList
        )
    }


}