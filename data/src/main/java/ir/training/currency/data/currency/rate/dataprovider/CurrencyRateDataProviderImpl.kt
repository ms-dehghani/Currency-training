package ir.training.currency.data.currency.rate.dataprovider

import ir.training.currency.data.ServiceRepository
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import javax.inject.Inject


internal class CurrencyRateDataProviderImpl @Inject constructor(private val repository: ServiceRepository) :
    CurrencyRateDataProvider {

    override suspend fun getUpdatedRates(): List<CurrencyRateItem> {
        return repository.getUpdatedRates()
    }

}