package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.limitDigits
import com.oguzhanozgokce.finishmarmarab2b.core.domain.util.CardNumberVisualTransformation.cardNumberVisualTransformation
import com.oguzhanozgokce.finishmarmarab2b.core.domain.util.CvvVisualTransformation
import com.oguzhanozgokce.finishmarmarab2b.core.domain.util.VisualTransformations.expirationDateTransformation
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlineTextField
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder

@Composable
fun FMNewCreditCart(
    uiState: PaymentContract.UiState,
    onAction: (PaymentContract.UiAction) -> Unit,
) {
    val builder = rememberBalloonBuilder {
        setArrowSize(10)
        setArrowPosition(0.5f)
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setPadding(12)
        setMarginHorizontal(12)
        setCornerRadius(8f)
        setBackgroundColorResource(R.color.gray)
        setBalloonAnimation(BalloonAnimation.ELASTIC)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        FMOutlineTextField(
            value = uiState.cardName,
            onValueChange = { onAction(PaymentContract.UiAction.OnChangeCardName(it)) },
            modifier = Modifier.fillMaxWidth(),
            indicatorsColor = colors.text.copy(alpha = 0.3f),
            label = stringResource(R.string.card_name),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        FMOutlineTextField(
            value = uiState.cardNumber,
            onValueChange = { newValue ->
                onAction(PaymentContract.UiAction.OnChangeCardNumber(newValue.limitDigits(16)))
            },
            modifier = Modifier.fillMaxWidth(),
            indicatorsColor = colors.text.copy(alpha = 0.3f),
            label = stringResource(R.string.card_number),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            visualTransformation = cardNumberVisualTransformation
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.weight(0.4f)
            ) {
                FMOutlineTextField(
                    value = uiState.expirationDateValue,
                    onValueChange = {
                        onAction(
                            PaymentContract.UiAction.OnChangeExpirationDate(
                                it.limitDigits(
                                    4
                                )
                            )
                        )
                    },
                    visualTransformation = expirationDateTransformation,
                    modifier = Modifier.fillMaxWidth(),
                    indicatorsColor = colors.text.copy(alpha = 0.3f),
                    label = stringResource(R.string.expiration_date)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(0.4f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FMOutlineTextField(
                        value = uiState.cvv,
                        onValueChange = { onAction(PaymentContract.UiAction.OnChangeCvv(it)) },
                        visualTransformation = CvvVisualTransformation(),
                        modifier = Modifier.weight(1f),
                        indicatorsColor = colors.text.copy(alpha = 0.3f),
                        label = stringResource(R.string.cvv),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        trailingIcon = {
                            Balloon(
                                builder = builder,
                                balloonContent = {
                                    Text(text = stringResource(R.string.cvv_info))
                                }
                            ) { balloonWindow ->
                                IconButton(
                                    onClick = {
                                        balloonWindow.showAlignBottom()
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = stringResource(R.string.cvv),
                                        tint = colors.lightGray
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FMNewCreditCartPreview() {
    FMTheme {
        FMNewCreditCart(
            uiState = PaymentContract.UiState(),
            onAction = {}
        )
    }
}
