package ir.training.currency.domain.model.currency.rate

data class CurrencyRateItem(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
