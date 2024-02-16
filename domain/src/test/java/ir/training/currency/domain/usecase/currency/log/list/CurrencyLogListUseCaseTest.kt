package ir.training.currency.domain.usecase.currency.log.list

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.domain.model.CurrencyLogItem
import ir.training.currency.domain.model.CurrencyLogType
import ir.training.currency.domain.repository.currency.log.CurrencyLogRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyLogListUseCaseTest {

    private val mockRepository = mockk<CurrencyLogRepository>()

    private val useCase = CurrencyLogListUseCase(mockRepository)

    @Test
    fun whenGetCurrencyLogListInvokedThenExpectedCurrencyLogReturned() =
        runBlocking {
            val expectedItem = CurrencyLogItem(
                type = CurrencyLogType.SELL,
                currencyName = "base",
                amount = 100.0,
            )

            coEvery { mockRepository.getCurrencyLogList() } returns listOf(expectedItem)

            val item = useCase.invoke()
            assertEquals(listOf(expectedItem), item)
        }

    @Test
    fun whenGetCurrencyLogListInvokedThenEmptyListReturned() =
        runBlocking {
            coEvery { mockRepository.getCurrencyLogList() } returns listOf()

            val item = useCase.invoke()
            assertEquals(0, item.size)
        }
}