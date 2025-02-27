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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.domain.util.CardNumberVisualTransformation.cardNumberVisualTransformation
import com.oguzhanozgokce.finishmarmarab2b.core.domain.util.VisualTransformations.expirationDateTransformation
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlineTextField
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

@Composable
fun FMNewCreditCart(
    uiState: PaymentContract.UiState,
    onAction: (PaymentContract.UiAction) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        FMOutlineTextField(
            value = uiState.cardName,
            onValueChange = { onAction(PaymentContract.UiAction.OnChangeCardName(it)) },
            modifier = Modifier.fillMaxWidth(),
            indicatorsColor = colors.text.copy(alpha = 0.3f),
            label = "Card Name Surname",
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        FMOutlineTextField(
            value = uiState.cardNumber.toString(),
            onValueChange = { onAction(PaymentContract.UiAction.OnChangeCardNumber(it)) },
            modifier = Modifier.fillMaxWidth(),
            indicatorsColor = colors.text.copy(alpha = 0.3f),
            label = "Card Number",
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
                    value = uiState.expirationDateValue.toString(),
                    onValueChange = { onAction(PaymentContract.UiAction.OnChangeExpirationDate(it)) },
                    visualTransformation = expirationDateTransformation,
                    modifier = Modifier.fillMaxWidth(),
                    indicatorsColor = colors.text.copy(alpha = 0.3f),
                    label = "Expiration Date"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(0.4f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FMOutlineTextField(
                        value = uiState.cvv.toString(),
                        onValueChange = { onAction(PaymentContract.UiAction.OnChangeCvv(it)) },
                        modifier = Modifier.weight(1f),
                        indicatorsColor = colors.text.copy(alpha = 0.3f),
                        label = "CVV",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "CVV",
                                tint = colors.lightGray
                            )
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
            uiState = PaymentContract.UiState()
        )

    }
}