package ir.training.currency.domain.usecase.currency.item.rate

import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.repository.currency.item.rate.CurrencyRateRepository

class CurrencyRateUseCase(private val repository: CurrencyRateRepository) {
    suspend operator fun invoke():List<CurrencyRateItem> {
        return repository.getUpdatedRates()
    }
}