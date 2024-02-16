package ir.training.currency.domain.model

data class CurrencyLogItem(
    val type: CurrencyLogType,
    val currencyName: String,
    val amount: Double
)

enum class CurrencyLogType {
    SELL,
    BUY
}
