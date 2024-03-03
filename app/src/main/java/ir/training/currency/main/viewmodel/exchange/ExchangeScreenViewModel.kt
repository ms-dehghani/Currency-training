package ir.training.currency.main.viewmodel.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.usecase.currency.item.exchange.CurrencyExchangeUseCase
import ir.training.currency.domain.usecase.currency.item.exchange.CurrencyFakeExchangeUseCase
import ir.training.currency.domain.usecase.currency.item.rate.CurrencyRateUseCase
import ir.training.currency.main.state.ExchangePageState
import ir.training.currency.main.state.base.PageState
import ir.training.currency.main.view.pages.exchange.contract.ExchangePageEffect
import ir.training.currency.main.view.pages.exchange.contract.ExchangePageEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeScreenViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val currencyExchangeUseCase: CurrencyExchangeUseCase,
    private val currencyFakeExchangeUseCase: CurrencyFakeExchangeUseCase,
    private val currencyRateUseCase: CurrencyRateUseCase,
) : ViewModel() {

    private var currencyRateList = listOf<CurrencyRateItem>()

    private val _state = MutableStateFlow(ExchangePageState())
    val state: StateFlow<ExchangePageState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<ExchangePageEffect>(1)
    val effectFlow = _effectFlow.asSharedFlow()

    init {
        getCurrencyRate(dispatcher)
    }

    private fun getCurrencyRate(dispatcher: CoroutineDispatcher) {
        viewModelScope.launch(dispatcher) {
            while (true) {
                currencyRateList = currencyRateUseCase.invoke()
                _state.update {
                    it.copy(
                        availableCurrencies = flowOf(currencyRateList[0].rates.keys.toList()),
                    )
                }
                delay(5_000)
            }
        }
    }


    fun onEvent(event: ExchangePageEvent) {
        when (event) {
            is ExchangePageEvent.ExchangeCurrency -> {
                exchangeCurrency(
                    dispatcher = dispatcher,
                    from = event.from,
                    to = event.to,
                    amount = event.amount
                )
            }

            is ExchangePageEvent.CurrencyExchangeResult -> {
                exchangeFakeCurrency(
                    dispatcher = dispatcher,
                    from = event.from,
                    to = event.to,
                    amount = event.amount
                )
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun exchangeCurrency(
        dispatcher: CoroutineDispatcher,
        from: String,
        to: String,
        amount: Double
    ) {
        viewModelScope.launch(dispatcher) {
            _state.update {
                it.copy(
                    pageState = PageState.LOADING
                )
            }
            val exchangeItem = currencyExchangeUseCase.invoke(
                from = from,
                to = to,
                amount = amount,
                currencyList = currencyRateList
            )
            _state.update {
                it.copy(
                    pageState = PageState.IDLE,
                    exchangeResponse = exchangeItem
                )
            }

            _effectFlow.tryEmit(
                ExchangePageEffect.OnExchangeResponseReceived(
                    message = exchangeItem.response
                )
            )
            _effectFlow.resetReplayCache()
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun exchangeFakeCurrency(
        dispatcher: CoroutineDispatcher,
        from: String,
        to: String,
        amount: Double
    ) {
        viewModelScope.launch(dispatcher) {
            _state.update {
                it.copy(
                    pageState = PageState.LOADING
                )
            }

            val exchangeItem = currencyFakeExchangeUseCase.invoke(
                from = from,
                to = to,
                amount = amount,
                currencyList = currencyRateList
            )

            _state.update {
                it.copy(
                    pageState = PageState.IDLE,
                )
            }

            _effectFlow.tryEmit(
                ExchangePageEffect.OnFakeExchangeResponseReceived(
                    amount = exchangeItem.amount
                )
            )
            _effectFlow.resetReplayCache()
        }
    }

}