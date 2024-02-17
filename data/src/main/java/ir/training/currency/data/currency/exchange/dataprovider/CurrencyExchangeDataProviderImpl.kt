package ir.training.currency.data.currency.exchange.dataprovider

import ir.training.currency.data.ServiceRepository
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem
import javax.inject.Inject


internal class CurrencyExchangeDataProviderImpl @Inject constructor(private val repository: ServiceRepository) :
    CurrencyExchangeDataProvider {

    override suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Double,
        currencyList: List<CurrencyRateItem>
    ): ExchangeItem {
        return repository.convertCurrency(
            from = from,
            to = to,
            amount = amount,
            currencyList = currencyList
        )
    }

}