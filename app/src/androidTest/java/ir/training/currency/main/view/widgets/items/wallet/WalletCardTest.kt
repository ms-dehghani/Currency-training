package ir.training.currency.main.view.widgets.items.wallet

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class WalletCardTest {

    @get: Rule
    val composeTestRule = createComposeRule()


    @Test
    fun givenBalance_WhenViewCreate_ThenShouldFindText() {
        val balance = "100"
        composeTestRule.setContent {
            WalletCard(balance)
        }
        composeTestRule.onNodeWithText(balance).assertExists()
    }
}