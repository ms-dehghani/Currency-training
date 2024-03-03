package ir.training.currency.domain.usecase.currency.item.exchange

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.domain.model.wallet.WalletCurrency
import ir.training.currency.domain.model.wallet.WalletItem
import ir.training.currency.domain.repository.currency.item.exchange.CurrencyExchangeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyFakeExchangeUseCaseTest {

    private val mockRepository = mockk<CurrencyExchangeRepository>()

    private val useCase = CurrencyFakeExchangeUseCase(mockRepository)

    @Test
    fun givenExchangeDataWhenConvertFakeCurrencyInvokedThenExpectedItemReturned() =
        runBlocking {
            val expectedItem = ExchangeItem(
                response = "response",
                walletItem = WalletItem(mutableListOf(WalletCurrency("USD", 1.0))),
                logList = emptyList()
            )

            coEvery {
                mockRepository.convertFakeCurrency(
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns expectedItem

            val item = useCase.invoke("", "", 1.0, listOf())
            assertEquals(expectedItem, item)
        }
}