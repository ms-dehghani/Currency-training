package ir.training.currency.data.service.dto.api.currency

import ir.training.currency.data.service.dto.api.currency.rate.CurrencyRateResponse
import ir.training.currency.domain.model.CurrencyRateItem

class CurrencyRateResponseAdapterToCurrencyRate {

    fun map(comicResponse: CurrencyRateResponse): CurrencyRateItem {
        return CurrencyRateItem(
            rates = comicResponse.rates,
            base = comicResponse.base,
            date = comicResponse.date,
        )
    }

}