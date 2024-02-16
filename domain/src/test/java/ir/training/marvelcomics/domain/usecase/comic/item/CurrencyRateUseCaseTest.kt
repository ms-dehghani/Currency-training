package ir.training.currency.domain.usecase.currency.item

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.domain.model.CurrencyRateItem
import ir.training.currency.domain.repository.currency.item.CurrencyRateRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyRateUseCaseTest {

    private val mockRepository = mockk<CurrencyRateRepository>()

    private val getCurrencyRateUseCase = CurrencyRateUseCase(mockRepository)

    @Test
    fun givenCurrencyRateIdWhenGetCurrencyRateUseCaseInvokedThenExpectedCurrencyRateReturned() =
        runBlocking {
            val expectedCurrencyRateItem = CurrencyRateItem(
                rates = mapOf(),
                base = "base",
                date = "date",
            )

            coEvery { mockRepository.getUpdatedRates() } returns listOf(expectedCurrencyRateItem)

            val item = getCurrencyRateUseCase.invoke()
            assertEquals(expectedCurrencyRateItem, item)
        }

    @Test
    fun givenCurrencyRateIdWhenGetCurrencyRateUseCaseInvokedAndCurrencyRateIsNotFoundThenEmptyCurrencyRateReturned() =
        runBlocking {
            coEvery { mockRepository.getUpdatedRates() } returns listOf()

            val item = getCurrencyRateUseCase.invoke()
            assertEquals(0, item.size)
        }
}