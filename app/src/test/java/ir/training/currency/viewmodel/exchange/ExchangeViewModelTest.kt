package ir.training.currency.viewmodel.exchange

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import ir.training.currency.domain.model.currency.rate.CurrencyRateItem
import ir.training.currency.domain.model.exchange.ExchangeItem
import ir.training.currency.domain.model.exchange.FakeExchangeItem
import ir.training.currency.domain.model.wallet.WalletItem
import ir.training.currency.domain.usecase.currency.item.exchange.CurrencyExchangeUseCase
import ir.training.currency.domain.usecase.currency.item.exchange.CurrencyFakeExchangeUseCase
import ir.training.currency.domain.usecase.currency.item.rate.CurrencyRateUseCase
import ir.training.currency.main.state.base.PageState
import ir.training.currency.main.view.pages.exchange.contract.ExchangePageEffect
import ir.training.currency.main.view.pages.exchange.contract.ExchangePageEvent
import ir.training.currency.main.viewmodel.exchange.ExchangeScreenViewModel
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
class ExchangeViewModelTest {

    private lateinit var viewModel: ExchangeScreenViewModel
    private var currencyRateUseCase = mockk<CurrencyRateUseCase>()
    private var currencyExchangeUseCase = mockk<CurrencyExchangeUseCase>()
    private var currencyFakeExchangeUseCase = mockk<CurrencyFakeExchangeUseCase>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        coEvery {
            currencyRateUseCase.invoke()
        } coAnswers {
            yield()
            listOf(CurrencyRateItem("1","", mapOf("1" to 1.0, "2" to 2.0)))
        }


        viewModel = ExchangeScreenViewModel(
            currencyExchangeUseCase = currencyExchangeUseCase,
            currencyRateUseCase = currencyRateUseCase,
            currencyFakeExchangeUseCase = currencyFakeExchangeUseCase,
            dispatcher = Dispatchers.Default
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
            assertEquals(PageState.LOADING, awaitItem().pageState)
        }
    }

    @Test
    fun whenCurrencyExchangeResultEventCalledThenExchangeFakeCurrencyShouldBeCalled() = runTest {

        val expectedItem = FakeExchangeItem("100", "")

        coEvery {
            currencyFakeExchangeUseCase.invoke(any(), any(), any(), any())
        } coAnswers {
            yield()
            expectedItem
        }

        viewModel.state.test {
            viewModel.onEvent(ExchangePageEvent.CurrencyExchangeResult("1", "2", 100.0))

            assertEquals(PageState.LOADING, awaitItem().pageState)
            assertEquals(PageState.IDLE, awaitItem().pageState)
        }

        viewModel.effectFlow.test {
            viewModel.onEvent(ExchangePageEvent.CurrencyExchangeResult("1", "2", 100.0))

            val item = awaitItem()

            Assert.assertNotNull(item)
            Assert.assertTrue(item is ExchangePageEffect.OnFakeExchangeResponseReceived)
            Assert.assertTrue((item as ExchangePageEffect.OnFakeExchangeResponseReceived).amount == expectedItem.amount)
        }

    }


    @Test
    fun whenExchangeCurrencyEventCalledThenExchangeCurrencyShouldBeCalled() = runTest {

        val expectedItem = ExchangeItem("100", WalletItem(mutableListOf()), listOf())

        coEvery {
            currencyExchangeUseCase.invoke(any(), any(), any(), any())
        } coAnswers {
            yield()
            expectedItem
        }

        viewModel.state.test {
            viewModel.onEvent(ExchangePageEvent.ExchangeCurrency("1", "2", 100.0))

            assertEquals(PageState.LOADING, awaitItem().pageState)
            assertEquals(PageState.IDLE, awaitItem().pageState)
        }

        viewModel.effectFlow.test {
            viewModel.onEvent(ExchangePageEvent.ExchangeCurrency("1", "2", 100.0))

            val item = awaitItem()

            Assert.assertNotNull(item)
            Assert.assertTrue(item is ExchangePageEffect.OnExchangeResponseReceived)
            Assert.assertTrue((item as ExchangePageEffect.OnExchangeResponseReceived).message == expectedItem.response)
        }

    }


}