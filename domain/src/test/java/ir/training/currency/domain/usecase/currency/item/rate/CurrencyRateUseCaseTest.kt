package ir.training.currency.domain.usecase.currency.item.rate

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.repository.currency.item.rate.CurrencyRateRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyRateUseCaseTest {

    private val mockRepository = mockk<CurrencyRateRepository>()

    private val useCase = CurrencyRateUseCase(mockRepository)

    @Test
    fun whenGetCurrencyRateUseCaseInvokedThenExpectedListReturned() =
        runBlocking {
            val expectedItem = CurrencyRateItem(
                rates = mapOf(),
                base = "base",
                date = "date",
            )

            coEvery { mockRepository.getUpdatedRates() } returns listOf(expectedItem)

            val item = useCase.invoke()
            assertEquals(listOf(expectedItem), item)
        }

    @Test
    fun whenGetCurrencyRateUseCaseInvokedThenEmptyListReturned() =
        runBlocking {
            coEvery { mockRepository.getUpdatedRates() } returns listOf()

            val item = useCase.invoke()
            assertEquals(0, item.size)
        }
}