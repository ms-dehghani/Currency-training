package ir.training.currency.main.state

import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.wallet.WalletItem
import ir.training.currency.main.state.base.PageState

data class WalletPageState(
    val pageState: PageState = PageState.LOADING,
    val wallet: WalletItem? = null,
    val logList: List<CurrencyLogItem> = emptyList()
)