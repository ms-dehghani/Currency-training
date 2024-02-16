package ir.training.currency.data.currency.rate.repository

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.data.currency.rate.dataprovider.CurrencyRateDataProvider
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class CurrencyRateRepositoryImplTest {

    @Test
    fun whenGetCurrencyRateInvoked_ThenExpectedCurrencyRatesItemReturned() = runBlocking {
        // Given
        val mockDataProvider = mockk<CurrencyRateDataProvider>()
        val currencyRateItemRepository = CurrencyRateRepositoryImpl(mockDataProvider)
        val expectedCurrencyRateItem = CurrencyRateItem(
            date = "date",
            base = "base",
            rates = mapOf(),
        )

        coEvery {
            mockDataProvider.getUpdatedRates()
        } returns listOf(expectedCurrencyRateItem)

        val item = currencyRateItemRepository.getUpdatedRates()

        assertEquals(listOf(expectedCurrencyRateItem), item)
    }

    @Test
    fun whenGetCurrencyRateByIdInvoked_ThenEmptyListReturned() = runBlocking {
        val mockDataProvider = mockk<CurrencyRateDataProvider>()
        val currencyRateItemRepository = CurrencyRateRepositoryImpl(mockDataProvider)

        coEvery {
            mockDataProvider.getUpdatedRates()
        } returns listOf()

        val item = currencyRateItemRepository.getUpdatedRates()

        assertEquals(0, item.size)
    }

}