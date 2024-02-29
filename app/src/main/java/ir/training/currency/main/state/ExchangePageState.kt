package ir.training.currency.main.state

import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.main.state.base.PageState
import kotlinx.coroutines.flow.Flow

data class ExchangePageState(
    val pageState: PageState = PageState.LOADING,
    val availableCurrencies: Flow<List<String>>? = null,
    val exchangeResponse: ExchangeItem? = null,
)