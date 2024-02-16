package ir.training.currency.data.currency.log.repository

import ir.training.currency.data.currency.log.dataprovider.CurrencyLogDataProvider
import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.repository.currency.log.CurrencyLogRepository
import javax.inject.Inject

internal class CurrencyLogRepositoryImpl @Inject constructor(private val dataProvider: CurrencyLogDataProvider) :
    CurrencyLogRepository {

    override suspend fun getCurrencyLogList(): List<CurrencyLogItem> {
        return dataProvider.getCurrencyLogList()
    }

    override suspend fun addLog(log: CurrencyLogItem): Boolean {
        return dataProvider.addLog(log)
    }

}