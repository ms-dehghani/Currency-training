package ir.training.currency.main.view.widgets.dialog.exchange

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun ExchangeDialogPreview() {
    ExchangeDialog("1000", {}, onExchangeCurrencyResponse = { s: String -> })
}

@Preview
@Composable
fun ExchangeDialogWithoutCurrencyListPreview() {
    ExchangeDialog("1000", {}, onExchangeCurrencyResponse = { s: String -> })
}