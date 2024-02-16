package ir.training.currency.data

import ir.training.currency.domain.model.CurrencyLogItem
import ir.training.currency.domain.model.CurrencyRateItem

interface ServiceRepository {

    suspend fun getUpdatedRates(): List<CurrencyRateItem>

    suspend fun getCurrencyLogList(): List<CurrencyLogItem>

    suspend fun addLog(log: CurrencyLogItem): Boolean

}