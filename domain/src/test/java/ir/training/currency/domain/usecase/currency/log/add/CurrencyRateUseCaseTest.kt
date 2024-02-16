package ir.training.currency.domain.usecase.currency.log.add

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.log.CurrencyLogType
import ir.training.currency.domain.repository.currency.log.CurrencyLogRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CurrencyRateUseCaseTest {

    private val mockRepository = mockk<CurrencyLogRepository>()

    private val useCase = CurrencyLogAddUseCase(mockRepository)

    @Test
    fun givenCurrencyLogWhenSddLogInvokedThenTrueReturned() =
        runBlocking {
            coEvery { mockRepository.addLog(any()) } returns true
            val item = useCase.invoke(
                CurrencyLogItem(type = CurrencyLogType.SELL, currencyName = "USD", amount = 1.0)
            )
            assertTrue(item)
        }

    @Test
    fun givenCurrencyLogWhenSddLogInvokedThenFlaseReturned() =
        runBlocking {
            coEvery { mockRepository.addLog(any()) } returns false
            val item = useCase.invoke(
                CurrencyLogItem(type = CurrencyLogType.SELL, currencyName = "USD", amount = 1.0)
            )
            assertFalse(item)
        }
}