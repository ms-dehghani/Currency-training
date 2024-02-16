package ir.training.currency.data.currency.rate.repository

import ir.training.currency.data.currency.rate.dataprovider.CurrencyRateDataProvider
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.repository.currency.item.rate.CurrencyRateRepository
import javax.inject.Inject

internal class CurrencyRateRepositoryImpl @Inject constructor(private val dataProvider: CurrencyRateDataProvider) :
    CurrencyRateRepository {

    override suspend fun getUpdatedRates(): List<CurrencyRateItem> {
        return dataProvider.getUpdatedRates()
    }

}