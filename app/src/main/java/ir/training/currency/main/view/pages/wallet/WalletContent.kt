package ir.training.currency.main.view.pages.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ir.training.currency.R
import ir.training.currency.main.state.WalletPageState
import ir.training.currency.main.view.widgets.dialog.exchange.ExchangeDialog
import ir.training.currency.main.view.widgets.items.log.ExchangeLogListItem
import ir.training.currency.main.view.widgets.items.wallet.WalletCard

@Composable
fun WalletContent(
    state: WalletPageState,
    modifier: Modifier = Modifier,
    onExchangeCurrencyResponse: (message: String) -> Unit,
) {

    val showExchangeDialog = remember { mutableStateOf(false) }
    when {
        showExchangeDialog.value -> {
            ExchangeDialog(
                state.wallet!!.getEuroAmount(),
                onDismissRequest = {
                    showExchangeDialog.value = false
                },
                onExchangeCurrencyResponse
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.page_background))
            .padding(dimensionResource(id = R.dimen.page_padding)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            WalletCard(balance = state.wallet!!.toStringValue())
            Box(
                Modifier
                    .weight(1f)
            ) {
                LazyColumn {
                    items(state.logList.size) {
                        ExchangeLogListItem(logItem = state.logList[it])
                        Divider(color = Color.LightGray)
                    }
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showExchangeDialog.value = true
                }) {
                Text(text = stringResource(id = R.string.exchange))
            }
        }

    }
}