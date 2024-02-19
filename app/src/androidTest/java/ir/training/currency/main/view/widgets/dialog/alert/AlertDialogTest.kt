package ir.training.currency.main.view.widgets.dialog.alert

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class AlertDialogTest {

    @get: Rule
    val composeTestRule = createComposeRule()


    @Test
    fun givenData_WhenViewCreate_ThenShouldFindText() {
        composeTestRule.setContent {
            AlertDialog("title", "text", {})
        }
        composeTestRule.onNodeWithText("text").assertExists()
    }

    @Test
    fun givenData_WhenViewCreate_ThenShouldFindTitle() {
        composeTestRule.setContent {
            AlertDialog("title", "text", {})
        }
        composeTestRule.onNodeWithText("title").assertExists()
    }
}