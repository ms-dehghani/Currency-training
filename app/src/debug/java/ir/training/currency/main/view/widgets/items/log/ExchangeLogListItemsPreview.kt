package ir.training.currency.main.view.widgets.items.log

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.log.CurrencyLogType


@Preview
@Composable
fun ExchangeLogListItemsSellPreview() {
    ExchangeLogListItem(CurrencyLogItem(CurrencyLogType.SELL , "EUR", 10.0))
}

@Preview
@Composable
fun ExchangeLogListItemsBuyPreview() {
    ExchangeLogListItem(CurrencyLogItem(CurrencyLogType.BUY , "EUR", 10.0))
}