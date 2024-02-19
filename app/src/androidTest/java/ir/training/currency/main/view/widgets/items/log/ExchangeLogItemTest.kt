package ir.training.currency.main.view.widgets.items.log

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.log.CurrencyLogType
import org.junit.Rule
import org.junit.Test

class ExchangeLogItemTest {

    @get: Rule
    val composeTestRule = createComposeRule()


    @Test
    fun givenLogItem_WhenViewCreate_ThenShouldFindText() {
        val item =
            CurrencyLogItem(type = CurrencyLogType.SELL, currencyName = "EUR", amount = 100.0)
        showLog(item)
        composeTestRule.onNodeWithText(item.currencyName).assertExists()
    }

    @Test
    fun givenLogItem_WhenViewCreate_ThenShouldFindAmount() {
        val item =
            CurrencyLogItem(type = CurrencyLogType.SELL, currencyName = "EUR", amount = 100.0)
        showLog(item)
        composeTestRule.onNodeWithText(item.amount.toString()).assertExists()
    }

    @Test
    fun givenLogItem_WhenViewCreate_ThenShouldFindType() {
        val item =
            CurrencyLogItem(type = CurrencyLogType.SELL, currencyName = "EUR", amount = 100.0)
        showLog(item)
        composeTestRule.onNodeWithText(CurrencyLogType.SELL.name).assertExists()
    }


    private fun showLog(logItem: CurrencyLogItem) {
        composeTestRule.setContent {
            ExchangeLogListItem(logItem)
        }
    }

}