package ir.training.currency.data.service.repository

import ir.training.currency.data.ServiceRepository
import ir.training.currency.data.service.dto.api.currency.CurrencyRateResponseAdapterToCurrencyRate
import ir.training.currency.data.service.repository.api.ApiService
import ir.training.currency.domain.model.CurrencyRateItem
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(val api: ApiService) :
    ServiceRepository {
    override suspend fun getUpdatedRates(): List<CurrencyRateItem> {
        val apiItem =
            api.getUpdatedRates()?.let {
                CurrencyRateResponseAdapterToCurrencyRate().map(it)
            }
        if (apiItem != null) {
            return listOf(apiItem)
        }
        return listOf()
    }
}

