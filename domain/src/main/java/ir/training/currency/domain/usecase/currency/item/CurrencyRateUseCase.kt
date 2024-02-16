package ir.training.currency.domain.usecase.currency.item

import ir.training.currency.domain.model.CurrencyRateItem
import ir.training.currency.domain.repository.currency.item.CurrencyRateRepository

class CurrencyRateUseCase(private val repository: CurrencyRateRepository) {
    suspend operator fun invoke():List<CurrencyRateItem> {
        return repository.getUpdatedRates()
    }
}