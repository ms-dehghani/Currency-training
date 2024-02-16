package ir.training.currency.domain.usecase.currency.log.add

import ir.training.currency.domain.model.CurrencyLogItem
import ir.training.currency.domain.repository.currency.log.CurrencyLogRepository

class CurrencyLogAddUseCase(private val repository: CurrencyLogRepository) {
    suspend operator fun invoke(logItem: CurrencyLogItem): Boolean {
        return repository.addLog(logItem)
    }
}