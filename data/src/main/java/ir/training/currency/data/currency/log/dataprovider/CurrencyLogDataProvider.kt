package ir.training.currency.data.currency.log.dataprovider

import ir.training.currency.domain.model.CurrencyLogItem

interface CurrencyLogDataProvider {
    suspend fun getCurrencyLogList(): List<CurrencyLogItem>
    suspend fun addLog(log: CurrencyLogItem): Boolean
}