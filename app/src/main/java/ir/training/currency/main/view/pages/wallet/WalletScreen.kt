package ir.training.currency.main.view.pages.wallet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ir.training.currency.R
import ir.training.currency.main.state.base.PageState
import ir.training.currency.main.view.pages.wallet.contract.WalletPageEffect
import ir.training.currency.main.view.pages.wallet.contract.WalletPageEvent

import ir.training.currency.main.view.widgets.dialog.alert.AlertDialog
import ir.training.currency.main.viewmodel.wallet.WalletScreenViewModel

@Composable
fun WalletScreen() {
    val viewModel: WalletScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val exchangeAlertDialogText = remember { mutableStateOf("") }

    when {
        exchangeAlertDialogText.value.isNotEmpty() -> {
            AlertDialog(
                stringResource(id = R.string.exchange_currency),
                exchangeAlertDialogText.value,
                onDismissRequest = {
                    exchangeAlertDialogText.value = ""
                })
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                is WalletPageEffect.OnExchangeResponseReceived -> {
                    exchangeAlertDialogText.value = effect.message
                }
            }
        }
    }


    if (state.pageState == PageState.LOADING && state.wallet == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            CircularProgressIndicator(
                color = colorResource(id = R.color.text_color_primary),
                modifier = Modifier.requiredSize(
                    dimensionResource(id = R.dimen.progress_size),
                    dimensionResource(id = R.dimen.progress_size)
                )
            )
        }
    } else {
        WalletContent(state = state, onExchangeCurrencyResponse = { message ->
            viewModel.onEvent(WalletPageEvent.ExchangeCurrencyResponse(message))
        })
    }
}