package ir.training.currency.data.currency.log.repository

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.data.currency.log.dataprovider.CurrencyLogDataProvider
import ir.training.currency.domain.model.CurrencyLogItem
import ir.training.currency.domain.model.CurrencyLogType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class CurrencyLogRepositoryImplTest {

    @Test
    fun whenGetCurrencyLogListInvoked_ThenExpectedItemSReturned() = runBlocking {
        // Given
        val mockDataProvider = mockk<CurrencyLogDataProvider>()
        val repository = CurrencyLogRepositoryImpl(mockDataProvider)
        val expectedItem = CurrencyLogItem(
            type = CurrencyLogType.SELL,
            amount = 1.0,
            currencyName = "USD",
        )

        coEvery {
            mockDataProvider.getCurrencyLogList()
        } returns listOf(expectedItem)

        val item = repository.getCurrencyLogList()

        assertEquals(listOf(expectedItem), item)
    }

    @Test
    fun whenGetCurrencyLogListInvoked_ThenEmptyListReturned() = runBlocking {
        val mockDataProvider = mockk<CurrencyLogDataProvider>()
        val repository = CurrencyLogRepositoryImpl(mockDataProvider)

        coEvery {
            mockDataProvider.getCurrencyLogList()
        } returns listOf()

        val item = repository.getCurrencyLogList()

        assertEquals(0, item.size)
    }

    @Test
    fun whenAddLogInvoked_ThenReturnTrue() = runBlocking {
        val mockDataProvider = mockk<CurrencyLogDataProvider>()
        val repository = CurrencyLogRepositoryImpl(mockDataProvider)

        coEvery {
            mockDataProvider.addLog(any())
        } returns true

        val item = repository.addLog(
            CurrencyLogItem(
                type = CurrencyLogType.SELL,
                amount = 1.0,
                currencyName = "USD",
            )
        )

        assertTrue(item)
    }

    @Test
    fun whenAddLogInvoked_ThenReturnFalse() = runBlocking {
        val mockDataProvider = mockk<CurrencyLogDataProvider>()
        val repository = CurrencyLogRepositoryImpl(mockDataProvider)

        coEvery {
            mockDataProvider.addLog(any())
        } returns false

        val item = repository.addLog(
            CurrencyLogItem(
                type = CurrencyLogType.SELL,
                amount = 1.0,
                currencyName = "USD",
            )
        )

        assertFalse(item)
    }

}