package ir.training.currency.data.service.dto.api.currency.rate

class CurrencyRateResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)