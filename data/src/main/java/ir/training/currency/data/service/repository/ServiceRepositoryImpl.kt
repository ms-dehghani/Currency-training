package ir.training.currency.data.service.repository

import ir.training.currency.data.ServiceRepository
import ir.training.currency.data.service.dto.api.currency.CurrencyRateResponseAdapterToCurrencyRate
import ir.training.currency.data.service.repository.api.ApiService
import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(val api: ApiService) :
    ServiceRepository {

    private var currencyLogList = ArrayList<CurrencyLogItem>()

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

    override suspend fun getCurrencyLogList(): List<CurrencyLogItem> {
        return currencyLogList
    }

    override suspend fun addLog(log: CurrencyLogItem): Boolean {
        return currencyLogList.add(log)
    }
}

