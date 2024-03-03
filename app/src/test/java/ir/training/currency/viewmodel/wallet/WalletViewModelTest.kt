package ir.training.currency.viewmodel.wallet

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.domain.model.wallet.WalletItem
import ir.training.currency.domain.usecase.currency.log.list.CurrencyLogListUseCase
import ir.training.currency.domain.usecase.wallet.WalletUseCase
import ir.training.currency.main.state.base.PageState
import ir.training.currency.main.view.pages.wallet.contract.WalletPageEffect
import ir.training.currency.main.view.pages.wallet.contract.WalletPageEvent
import ir.training.currency.main.viewmodel.wallet.WalletScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.yield
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class WalletViewModelTest {

    private lateinit var viewModel: WalletScreenViewModel
    private var walletUseCase = mockk<WalletUseCase>()
    private var currencyLogListUseCase = mockk<CurrencyLogListUseCase>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        coEvery {
            currencyLogListUseCase.invoke()
        } coAnswers {
            yield()
            listOf()
        }

        coEvery {
            walletUseCase.invoke()
        } coAnswers {
            yield()
            WalletItem(mutableListOf())
        }

        viewModel = WalletScreenViewModel(
            walletUseCase = walletUseCase,
            currencyLogListUseCase = currencyLogListUseCase,
            dispatcher = Dispatchers.IO
        )

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenInitialStateWhenViewModelCreatedThenStateShouldBeUpdated() = runTest {
        viewModel.state.test {
            assertEquals(PageState.IDLE, awaitItem().pageState)
        }
    }

    @Test
    fun whenEventCalledThenMethodShouldBeCalled() = runTest {
        viewModel.effectFlow.test {
            viewModel.onEvent(WalletPageEvent.ExchangeCurrencyResponse("Test"))

            val item = awaitItem()

            Assert.assertNotNull(item)
            Assert.assertTrue(item is WalletPageEffect.OnExchangeResponseReceived)
            Assert.assertTrue((item as WalletPageEffect.OnExchangeResponseReceived).message == "Test")

        }
    }

}