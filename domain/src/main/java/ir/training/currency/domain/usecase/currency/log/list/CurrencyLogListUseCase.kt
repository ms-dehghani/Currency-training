package ir.training.currency.domain.usecase.currency.log.list

import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.repository.currency.log.CurrencyLogRepository

class CurrencyLogListUseCase(private val repository: CurrencyLogRepository) {
    suspend operator fun invoke(): List<CurrencyLogItem> {
        return repository.getCurrencyLogList()
    }
}