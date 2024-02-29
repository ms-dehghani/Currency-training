package ir.training.currency.main.viewmodel.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.training.currency.domain.usecase.currency.log.list.CurrencyLogListUseCase
import ir.training.currency.domain.usecase.wallet.WalletUseCase
import ir.training.currency.main.state.WalletPageState
import ir.training.currency.main.state.base.PageState
import ir.training.currency.main.view.pages.wallet.contract.WalletPageEffect
import ir.training.currency.main.view.pages.wallet.contract.WalletPageEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletScreenViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val walletUseCase: WalletUseCase,
    private val currencyLogListUseCase: CurrencyLogListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(WalletPageState())
    val state: StateFlow<WalletPageState> = _state.asStateFlow()

    private val _effectFlow = MutableSharedFlow<WalletPageEffect>(1)
    val effectFlow = _effectFlow.asSharedFlow()

    init {
        getInitialState()
    }

    private fun getInitialState() {
        viewModelScope.launch(dispatcher) {
            val wallet = walletUseCase.invoke()
            val logList = currencyLogListUseCase.invoke()
            _state.update {
                it.copy(
                    pageState = PageState.IDLE,
                    wallet = wallet,
                    logList = logList,
                )
            }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun onEvent(event: WalletPageEvent) {
        when (event) {
            is WalletPageEvent.ExchangeCurrencyResponse -> {
                viewModelScope.launch {
                    _effectFlow.emit(WalletPageEffect.OnExchangeResponseReceived(event.message))
                    _effectFlow.resetReplayCache()
                }
            }
        }
    }

}