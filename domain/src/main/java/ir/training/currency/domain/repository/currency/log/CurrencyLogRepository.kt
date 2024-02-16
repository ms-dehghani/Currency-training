package ir.training.currency.domain.repository.currency.log

import ir.training.currency.domain.model.CurrencyLogItem

interface CurrencyLogRepository {
    suspend fun getCurrencyLogList(): List<CurrencyLogItem>
    suspend fun addLog(log: CurrencyLogItem): Boolean
}