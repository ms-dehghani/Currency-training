package ir.training.currency.main.view.pages.exchange.contract

sealed class ExchangePageEffect {
    data class OnExchangeResponseReceived(val message: String) : ExchangePageEffect()
}