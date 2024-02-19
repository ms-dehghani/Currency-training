package ir.training.currency.main.view.widgets.dialog.exchange

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class ExchangeDialogTest {

    @get: Rule
    val composeTestRule = createComposeRule()


    @Test
    fun givenData_WhenViewCreate_ThenShouldFindAmount() {
        composeTestRule.setContent {
            ExchangeDialog("1000", listOf(), {}, { s: String, d: Double -> })
        }
        composeTestRule.onNodeWithText("1000 available").assertExists()
    }

    @Test
    fun givenData_WhenViewCreate_ThenShouldFindButton() {
        composeTestRule.setContent {
            ExchangeDialog("100", listOf(), {}, { s: String, d: Double -> })
        }
        composeTestRule.onNodeWithText("Exchange").assertExists()
    }
}