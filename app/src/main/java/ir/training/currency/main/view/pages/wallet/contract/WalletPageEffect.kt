package ir.training.currency.main.view.pages.wallet.contract

sealed class WalletPageEffect {
    data class OnExchangeResponseReceived(val message: String) : WalletPageEffect()
}