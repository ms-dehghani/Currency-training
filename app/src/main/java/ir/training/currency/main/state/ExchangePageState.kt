package ir.training.currency.main.state

import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.main.state.base.PageState

data class ExchangePageState(
    val pageState: PageState = PageState.LOADING,
    val availableCurrencies: List<String>,
    val exchangeResponse: ExchangeItem? = null,
)