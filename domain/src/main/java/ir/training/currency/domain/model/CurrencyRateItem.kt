package ir.training.currency.domain.model

data class CurrencyRateItem(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
