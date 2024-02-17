package ir.training.currency.domain.usecase.wallet

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.domain.model.wallet.WalletCurrency
import ir.training.currency.domain.model.wallet.WalletItem
import ir.training.currency.domain.repository.wallet.WalletRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class WalletUseCaseTest {

    private val mockRepository = mockk<WalletRepository>()

    private val useCase = WalletUseCase(mockRepository)

    @Test
    fun whenGetCurrencyRateUseCaseInvokedThenExpectedListReturned() =
        runBlocking {
            val expectedItem = WalletItem(
                currencyList = mutableListOf(
                    WalletCurrency(
                        name = "USD",
                        amount = 1.0
                    )
                )
            )

            coEvery { mockRepository.getWallet() } returns expectedItem

            val item = useCase.invoke()
            assertEquals(expectedItem, item)
        }
}