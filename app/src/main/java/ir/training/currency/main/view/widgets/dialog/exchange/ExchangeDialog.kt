package ir.training.currency.main.view.widgets.dialog.exchange

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import ir.training.currency.R
import ir.training.currency.main.view.pages.exchange.contract.ExchangePageEffect
import ir.training.currency.main.view.pages.exchange.contract.ExchangePageEvent
import ir.training.currency.main.viewmodel.exchange.ExchangeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeDialog(
    availableAmount: String,
    onDismissRequest: () -> Unit,
    onExchangeCurrencyResponse: (message: String) -> Unit
) {

    val viewModel: ExchangeScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    var exchangeAmount by remember { mutableStateOf("10") }
    var selectedCurrency by remember { mutableStateOf("") }
    var dropDownMenuState by remember { mutableStateOf(false) }


    val availableCurrencies = if (state.availableCurrencies != null) {
        state.availableCurrencies!!.collectAsState(initial = emptyList()).value
    } else {
        emptyList()
    }

    if (selectedCurrency.isEmpty() && availableCurrencies.isNotEmpty()) {
        selectedCurrency = availableCurrencies[0]
    }

    LaunchedEffect(viewModel) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                is ExchangePageEffect.OnExchangeResponseReceived -> {
                    onExchangeCurrencyResponse.invoke(effect.message)
                }
            }
        }
    }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.exchange_card_height))
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .background(
                    color = colorResource(id = R.color.dialog_background),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_radios_medium))
                ),
        ) {

            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painterResource(R.drawable.exchange),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.width(
                        dimensionResource(id = R.dimen.exchange_icon_size)
                    )
                )

                Divider(
                    color = colorResource(id = R.color.white), modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )

                Column(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = exchangeAmount,
                            onValueChange = { exchangeAmount = it },
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = colorResource(id = R.color.transparent),
                                unfocusedIndicatorColor = colorResource(id = R.color.transparent),
                                disabledIndicatorColor = colorResource(id = R.color.transparent)
                            ),

                            keyboardOptions = KeyboardOptions(
                                keyboardType =
                                KeyboardType.Decimal
                            ),
                            prefix = { Text(text = "$ ", fontWeight = FontWeight.Bold) },
                            textStyle = TextStyle(fontWeight = FontWeight.Bold),
                            label = { Text(text = stringResource(id = R.string.amount_to_exchange)) }
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
                            text = "$availableAmount ${stringResource(id = R.string.available)}"
                        )
                    }


                    Divider(
                        color = colorResource(id = R.color.white), thickness = 1.dp,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                            text = stringResource(id = R.string.convert_to)
                        )

                        Text(selectedCurrency)
                        IconButton(onClick = { dropDownMenuState = !dropDownMenuState }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown"
                            )
                        }
                        DropdownMenu(
                            expanded = dropDownMenuState,
                            onDismissRequest = { dropDownMenuState = false }
                        ) {
                            availableCurrencies.forEach {
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    onClick = {
                                        dropDownMenuState = false
                                        selectedCurrency = it
                                    }
                                )
                                Divider(
                                    color = colorResource(id = R.color.white), thickness = 1.dp,
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        )
                        Button(onClick = {
                            viewModel.onEvent(
                                ExchangePageEvent.ExchangeCurrency(
                                    "EUR",
                                    selectedCurrency,
                                    exchangeAmount.toDouble()
                                )
                            )
                            onDismissRequest()
                        }) {
                            Text(text = stringResource(id = R.string.exchange))
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        )
                    }

                }
            }

        }
    }
}
