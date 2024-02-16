package ir.training.currency.data

import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem

interface ServiceRepository {

    suspend fun getUpdatedRates(): List<CurrencyRateItem>

    suspend fun getCurrencyLogList(): List<CurrencyLogItem>

    suspend fun addLog(log: CurrencyLogItem): Boolean

}