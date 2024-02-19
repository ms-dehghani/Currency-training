package ir.training.currency.main.view.widgets.items.wallet


import androidx.annotation.Px
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.training.currency.R
import ir.training.currency.main.theme.WalletGradientCenter
import ir.training.currency.main.theme.WalletGradientEnd
import ir.training.currency.main.theme.WalletGradientStart
import ir.training.currency.main.theme.WhiteText

@Composable
fun WalletCard(
    balance: String,
) {
    Card(
        modifier = Modifier
            .aspectRatio(ratio = 1.6f)
            .clip(RoundedCornerShape(dimensionResource(R.dimen.card_radios_medium)))

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            WalletGradientStart,
                            WalletGradientCenter,
                            WalletGradientEnd
                        )
                    )
                )
                .padding(dimensionResource(id = R.dimen.padding_large)),
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.balance),
                    fontWeight = FontWeight.Normal,
                    fontSize = dimensionResource(id = R.dimen.font_size_normal).value.sp,
                    maxLines = 1,
                    color = WhiteText,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.padding_large),
                        start = dimensionResource(id = R.dimen.padding_large),
                        end = dimensionResource(id = R.dimen.padding_large)
                    )
                )
                Text(
                    text = balance,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.font_size_large).value.sp,
                    maxLines = 4,
                    color = colorResource(id = R.color.white),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth().padding(
                        start = dimensionResource(id = R.dimen.padding_medium) * 3,
                        end = dimensionResource(id = R.dimen.padding_large)
                    )
                )
            }

        }
    }
}


@Preview
@Composable
fun WalletCardPreview() {
    WalletCard(
        "$100 EUR \n$100 USD"
    )
}