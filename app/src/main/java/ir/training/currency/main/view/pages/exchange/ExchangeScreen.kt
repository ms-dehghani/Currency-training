package ir.training.currency.main.view.pages.exchange

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
import ir.training.currency.main.di.DIApp
import ir.training.currency.main.state.base.PageState
import ir.training.currency.main.view.pages.exchange.contract.ExchangePageEffect
import ir.training.currency.main.view.pages.exchange.contract.ExchangePageEvent
import ir.training.currency.main.view.widgets.dialog.alert.AlertDialog
import ir.training.currency.main.viewmodel.exchange.ExchangeViewModel
import kotlinx.coroutines.Dispatchers

@Composable
fun ExchangeScreen() {
    val viewModel: ExchangeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    val showExchangeAlertDialog = remember { mutableStateOf(false) }
    when {
        showExchangeAlertDialog.value -> {
            AlertDialog(
                stringResource(id = R.string.exchange_currency),
                state.exchangeResponse!!.response,
                onDismissRequest = {
                    showExchangeAlertDialog.value = false
                })
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                is ExchangePageEffect.OnExchangeResponseReceived -> {
                    showExchangeAlertDialog.value = true
                }
            }
        }
    }


    if (state.pageState == PageState.LOADING && state.exchangeResponse == null) {
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
        ExchangeContent(state = state, onExchangeCurrency = { to, amount ->
            viewModel.onEvent(ExchangePageEvent.ExchangeCurrency(to, amount))
        })
    }
}