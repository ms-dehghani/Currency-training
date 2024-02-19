package ir.training.currency.main.view.pages.exchange.contract

sealed class ExchangePageEvent {
    data class ExchangeCurrency(val to: String, val amount: Double) : ExchangePageEvent()
}