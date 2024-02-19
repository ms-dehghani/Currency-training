package ir.training.currency.main.view.widgets.items.log

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import ir.training.currency.R
import ir.training.currency.domain.model.currency.log.CurrencyLogItem
import ir.training.currency.domain.model.currency.log.CurrencyLogType

@Composable
fun ExchangeLogListItem(
    logItem: CurrencyLogItem,
) {
    Box(
        modifier = Modifier
            .background(color = colorResource(id = R.color.item_background))
            .padding(vertical = dimensionResource(id = R.dimen.padding_small))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.log_item_type_size))
                    .height(dimensionResource(id = R.dimen.log_item_type_size))
                    .background(
                        color = colorResource(id = if (logItem.type == CurrencyLogType.SELL) R.color.red else R.color.green),
                        shape = CircleShape
                    )
            ) {
                Image(
                    painterResource(if (logItem.type == CurrencyLogType.SELL) R.drawable.arrow_up else R.drawable.arrow_down),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_small))
                )
            }

            Text(
                text = logItem.type.name,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.font_size_small).value.sp,
                maxLines = 1,
                color = colorResource(id = if (logItem.type == CurrencyLogType.SELL) R.color.red else R.color.green),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_large),
                        end = dimensionResource(id = R.dimen.padding_large)
                    )
            )
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = logItem.amount.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.font_size_small).value.sp,
                    maxLines = 1,
                    color = colorResource(id = R.color.text_color_primary),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_large),
                            end = dimensionResource(id = R.dimen.padding_large)
                        )
                )
            }
            Text(
                text = logItem.currencyName,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.font_size_small).value.sp,
                maxLines = 1,
                color = colorResource(id = R.color.text_color_primary),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_large),
                    end = dimensionResource(id = R.dimen.padding_large)
                )
            )

        }
    }
}