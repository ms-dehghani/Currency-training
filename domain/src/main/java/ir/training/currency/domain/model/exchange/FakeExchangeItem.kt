package ir.training.currency.domain.model.exchange

import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.wallet.WalletItem

data class FakeExchangeItem(
    val amount: String,
    val response: String,
)