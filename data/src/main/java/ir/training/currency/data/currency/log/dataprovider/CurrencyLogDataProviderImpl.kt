package ir.training.currency.data.currency.log.dataprovider

import ir.training.currency.data.ServiceRepository
import ir.training.currency.domain.model.CurrencyLogItem
import javax.inject.Inject


internal class CurrencyLogDataProviderImpl @Inject constructor(private val repository: ServiceRepository) :
    CurrencyLogDataProvider {
    override suspend fun getCurrencyLogList(): List<CurrencyLogItem> {
        return repository.getCurrencyLogList()
    }

    override suspend fun addLog(log: CurrencyLogItem): Boolean {
        return repository.addLog(log)
    }

}