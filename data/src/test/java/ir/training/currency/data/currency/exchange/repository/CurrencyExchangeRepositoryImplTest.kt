package ir.training.currency.data.currency.exchange.repository

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.data.currency.exchange.dataprovider.CurrencyExchangeDataProvider
import ir.training.currency.data.currency.log.dataprovider.CurrencyLogDataProvider
import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.log.CurrencyLogType
import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.domain.model.wallet.WalletItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class CurrencyExchangeRepositoryImplTest {

    @Test
    fun whenConvertCurrencyInvoked_ThenExpectedItemSReturned() = runBlocking {
        // Given
        val mockDataProvider = mockk<CurrencyExchangeDataProvider>()
        val repository = CurrencyExchangeRepositoryImpl(mockDataProvider)
        val expectedItem = ExchangeItem(response = "" , walletItem = WalletItem(mutableListOf()) , logList = listOf())

        coEvery {
            mockDataProvider.convertCurrency(
                any(),
                any(),
                any(),
                any()
            )
        } returns expectedItem

        val item = repository.convertCurrency("","",1.0 , listOf())

        assertEquals(expectedItem, item)
    }

}