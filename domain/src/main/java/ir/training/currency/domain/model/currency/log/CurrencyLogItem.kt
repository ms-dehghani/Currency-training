package ir.training.currency.domain.model.currency.log

data class CurrencyLogItem(
    val type: CurrencyLogType,
    val currencyName: String,
    val amount: Double
)