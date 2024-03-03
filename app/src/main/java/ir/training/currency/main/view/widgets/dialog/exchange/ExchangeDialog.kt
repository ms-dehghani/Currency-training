package ir.training.currency.main.view.widgets.dialog.exchange

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import ir.training.currency.R
import ir.training.currency.main.theme.WhiteText
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

    var exchangeAmount by remember { mutableStateOf("0") }
    var getCurrencyAmount by remember { mutableStateOf("0") }
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

                is ExchangePageEffect.OnFakeExchangeResponseReceived -> {
                    getCurrencyAmount = effect.amount
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
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .fillMaxHeight()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f),
                        Arrangement.Center
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = exchangeAmount,
                            onValueChange = {
                                exchangeAmount = it
                                viewModel.onEvent(
                                    ExchangePageEvent.CurrencyExchangeResult(
                                        "EUR",
                                        selectedCurrency,
                                        if (exchangeAmount.isEmpty()) 0.0 else exchangeAmount.toDouble()
                                    )
                                )
                            },
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
                            .weight(2f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                            fontWeight = FontWeight.Normal,
                            fontSize = dimensionResource(id = R.dimen.font_size_normal).value.sp,
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis,
                            text = if (getCurrencyAmount != "-") String.format(
                                stringResource(id = R.string.get_currency),
                                getCurrencyAmount
                            ) else "-"
                        )

                        Text(
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                            fontWeight = FontWeight.Normal,
                            fontSize = dimensionResource(id = R.dimen.font_size_normal).value.sp,
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis,
                            text = selectedCurrency
                        )

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
                                        ExchangePageEvent.ExchangeCurrency(
                                            "EUR",
                                            selectedCurrency,
                                            exchangeAmount.toDouble()
                                        )
                                    }
                                )
                                Divider(
                                    color = colorResource(id = R.color.white), thickness = 1.dp,
                                )
                            }
                        }

                    }

                    Button(modifier = Modifier
                        .align(Alignment.End),
                        onClick = {
                        if (exchangeAmount.isNotEmpty())
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

                }
            }

        }
    }
}
