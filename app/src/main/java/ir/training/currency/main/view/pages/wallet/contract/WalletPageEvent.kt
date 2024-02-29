package ir.training.currency.main.view.pages.wallet.contract

sealed class WalletPageEvent {
    data class ExchangeCurrencyResponse(val message: String) : WalletPageEvent()
}