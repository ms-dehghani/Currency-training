package ir.training.currency.data.wallet.repository

import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.data.wallet.rate.dataprovider.WalletDataProvider
import ir.training.currency.data.wallet.rate.repository.WalletRepositoryImpl
import ir.training.currency.domain.model.wallet.WalletCurrency
import ir.training.currency.domain.model.wallet.WalletItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test


class WalletRepositoryImplTest {

    @Test
    fun whenGetWalletInvoked_ThenExpectedItemReturned() = runBlocking {
        // Given
        val mockDataProvider = mockk<WalletDataProvider>()
        val repository = WalletRepositoryImpl(mockDataProvider)
        val expectedItem = WalletItem(
            currencyList = mutableListOf(
                WalletCurrency(
                    name = "USD",
                    amount = 1.0
                )
            )
        )

        coEvery {
            mockDataProvider.getWallet()
        } returns expectedItem

        val item = repository.getWallet()

        assertEquals(expectedItem, item)
    }

}