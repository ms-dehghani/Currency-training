package ir.training.currency.viewmodel.exchange

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.domain.model.wallet.WalletItem
import ir.training.currency.domain.usecase.currency.item.exchange.CurrencyExchangeUseCase
import ir.training.currency.domain.usecase.currency.item.rate.CurrencyRateUseCase
import ir.training.currency.domain.usecase.wallet.WalletUseCase
import ir.training.currency.main.state.base.PageState
import ir.training.currency.main.view.pages.exchange.contract.ExchangePageEffect
import ir.training.currency.main.view.pages.exchange.contract.ExchangePageEvent
import ir.training.currency.main.viewmodel.exchange.ExchangeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
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
class ExchangeViewModelTest {

    private lateinit var viewModel: ExchangeViewModel
    private var walletUseCase = mockk<WalletUseCase>()
    private var currencyRateUseCase = mockk<CurrencyRateUseCase>()
    private var currencyExchangeUseCase = mockk<CurrencyExchangeUseCase>()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        coEvery {
            currencyRateUseCase.invoke()
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

        viewModel = ExchangeViewModel(
            walletUseCase = walletUseCase,
            currencyExchangeUseCase = currencyExchangeUseCase,
            currencyRateUseCase = currencyRateUseCase
        )

    }

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
    fun givenValueWhenEventCalledThenMethodShouldBeCalled() = runTest {

        val expectedItem = ExchangeItem("", WalletItem(mutableListOf()), listOf())

        coEvery {
            currencyExchangeUseCase.invoke(any(), any(), any(), any())
        } coAnswers {
            yield()
            expectedItem
        }

        viewModel.state.test {
            assertEquals(PageState.IDLE, awaitItem().pageState)

            viewModel.onEvent(ExchangePageEvent.ExchangeCurrency("USD", 100.0))
            assertEquals(PageState.LOADING, awaitItem().pageState)
            assertEquals(PageState.IDLE, awaitItem().pageState)

            val effect = viewModel.effectFlow.first()
            Assert.assertNotNull(effect)
            Assert.assertTrue(effect is ExchangePageEffect.OnExchangeResponseReceived)

        }

    }

}