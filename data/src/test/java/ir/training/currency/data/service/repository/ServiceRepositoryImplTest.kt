package ir.training.currency.data.service.repository

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.data.service.dto.api.currency.rate.CurrencyRateResponse
import ir.training.currency.data.service.repository.api.ApiService
import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.log.CurrencyLogType
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.domain.model.wallet.WalletCurrency
import ir.training.currency.domain.model.wallet.WalletItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ServiceRepositoryImplTest {

    private lateinit var mockRepository: ServiceRepositoryImpl
    private lateinit var repository: ServiceRepositoryImpl
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        apiService = mockk<ApiService>()
        mockRepository = mockk<ServiceRepositoryImpl>()
        repository = ServiceRepositoryImpl(apiService)
    }

    @Test
    fun whenGetWalletInvoke_thenReturnExpectedWallet() = runBlocking {
        // Arrange
        val expectedWallet = WalletItem(
            currencyList = mutableListOf(
                WalletCurrency(
                    name = "USD",
                    amount = 1.0
                )
            )
        )

        coEvery {
            mockRepository.getWallet()
        } returns expectedWallet

        val item = mockRepository.getWallet()

        Assert.assertEquals(expectedWallet, item)
    }

    @Test
    fun whenGetUpdatedRatesInvoke_thenReturnExpectedResult() = runBlocking {
        // Arrange
        val expectedRates = listOf(CurrencyRateItem(base = "USD", date = "date", rates = mapOf()))

        coEvery {
            apiService.getUpdatedRates()
        } returns CurrencyRateResponse(
            base = "USD",
            date = "date",
            rates = mapOf()
        )

        // Act
        val resultRates = repository.getUpdatedRates()

        // Assert
        assertEquals(expectedRates, resultRates)
    }

    @Test
    fun givenEnoughCurrency_whenConvertCurrency_thenReturnSuccess() = runBlocking {
        // Arrange
        val from = "EUR"
        val to = "USD"
        val amount = 100.0
        val currencyList =
            listOf(CurrencyRateItem(base = from, date = "", rates = mapOf(to to 1.2)))
        val expectedExchangeItem = ExchangeItem(
            response = "You have converted 100.0 EUR to 120.0 USD.",
            walletItem = WalletItem(
                mutableListOf(
                    WalletCurrency(from, 900.0),
                    WalletCurrency(to, amount * 1.2)
                )
            ),
            logList = listOf(
                CurrencyLogItem(type = CurrencyLogType.SELL, currencyName = "EUR", amount = 100.0),
                CurrencyLogItem(type = CurrencyLogType.BUY, currencyName = "USD", amount = 120.0)
            )
        )

        val resultRates = repository.convertCurrency(from, to, amount, currencyList)

        // Assert
        assertEquals(expectedExchangeItem, resultRates)
    }

    @Test
    fun givenNotEnoughCurrency_whenConvertCurrency_thenReturnFailed() = runBlocking {
        // Arrange
        val from = "EUR"
        val to = "USD"
        val amount = 10000.0
        val currencyList =
            listOf(CurrencyRateItem(base = from, date = "", rates = mapOf(to to 1.2)))
        val expectedExchangeItem = ExchangeItem(
            response = "You don't have enough ${from}.",
            walletItem = WalletItem(
                mutableListOf(
                    WalletCurrency(from, 1000.0),
                )
            ),
            logList = emptyList()
        )

        val resultRates = repository.convertCurrency(from, to, amount, currencyList)

        // Assert
        assertEquals(expectedExchangeItem, resultRates)
    }

}