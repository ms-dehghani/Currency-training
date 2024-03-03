package ir.training.currency.domain.usecase.currency.item.exchange

import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.domain.model.exchange.FakeExchangeItem
import ir.training.currency.domain.repository.currency.item.exchange.CurrencyExchangeRepository

class CurrencyFakeExchangeUseCase(private val repository: CurrencyExchangeRepository) {
    suspend operator fun invoke(
        from: String, to: String, amount: Double,
        currencyList: List<CurrencyRateItem>
    ): FakeExchangeItem {
        return repository.convertFakeCurrency(
            from = from,
            to = to,
            amount = amount,
            currencyList = currencyList
        )
    }
}