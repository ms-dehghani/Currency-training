package ir.training.currency.domain.model.exchange

import ir.training.currency.domain.model.wallet.WalletItem

data class ExchangeItem (var response: String, var walletItem: WalletItem)