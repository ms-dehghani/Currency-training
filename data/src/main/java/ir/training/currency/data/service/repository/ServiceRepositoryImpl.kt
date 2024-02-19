package ir.training.currency.data.service.repository

import ir.training.currency.data.ServiceRepository
import ir.training.currency.data.service.dto.api.currency.CurrencyRateResponseAdapterToCurrencyRate
import ir.training.currency.data.service.repository.api.ApiService
import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.log.CurrencyLogType
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.domain.model.wallet.WalletCurrency
import ir.training.currency.domain.model.wallet.WalletItem
import java.math.RoundingMode
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(val api: ApiService) :
    ServiceRepository {

    private var currencyLogList = ArrayList<CurrencyLogItem>()
    private var wallet = WalletItem(mutableListOf(WalletCurrency("EUR", 1000.0)))
    override suspend fun getWallet(): WalletItem {
        return wallet
    }

    override suspend fun getUpdatedRates(): List<CurrencyRateItem> {
        val apiItem =
            api.getUpdatedRates()?.let {
                CurrencyRateResponseAdapterToCurrencyRate().map(it)
            }
        if (apiItem != null) {
            return listOf(apiItem)
        }
        return listOf()
    }

    override suspend fun getCurrencyLogList(): List<CurrencyLogItem> {
        return currencyLogList
    }

    override suspend fun addLog(log: CurrencyLogItem): Boolean {
        return currencyLogList.add(log)
    }

    override suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Double,
        currencyList: List<CurrencyRateItem>
    ): ExchangeItem {

        val commission = if (currencyLogList.size > 2) 0.007 else 0.0

        val fromCurrency = currencyList.find { it.base == from }
        fromCurrency?.let {
            fromCurrency.rates[to]?.let {
                val newCurrencyAmount = roundNumber(amount * it, 2)
                val currencyExchangeCommission = roundNumber(amount * commission, 2)

                wallet.addCurrency(WalletCurrency(to, newCurrencyAmount))
                wallet.addCurrency(WalletCurrency(from, -roundNumber((amount + currencyExchangeCommission),2)))

                addLog(
                    CurrencyLogItem(
                        type = CurrencyLogType.SELL,
                        currencyName = from,
                        amount = amount
                    )
                )
                if (currencyExchangeCommission > 0.0) {
                    addLog(
                        CurrencyLogItem(
                            type = CurrencyLogType.SELL,
                            currencyName = "Commission",
                            amount = currencyExchangeCommission
                        )
                    )
                }
                addLog(
                    CurrencyLogItem(
                        type = CurrencyLogType.BUY,
                        currencyName = to,
                        amount = newCurrencyAmount
                    )
                )

                return ExchangeItem(
                    response = "You have converted $amount $from to $newCurrencyAmount ${to}."
                            + if (currencyExchangeCommission > 0.0) " Commission Fee - $currencyExchangeCommission ${from}." else "",
                    walletItem = wallet,
                    logList = getCurrencyLogList()
                )
            }
            return ExchangeItem(
                response = "Operation Failed.",
                walletItem = wallet,
                logList = getCurrencyLogList()
            )
        }
        return ExchangeItem(
            response = "Operation Failed.",
            walletItem = wallet,
            logList = getCurrencyLogList()
        )
    }


    private fun roundNumber(number: Double, places: Int): Double {
        return number.toBigDecimal().setScale(places, RoundingMode.UP).toDouble()
    }
}

