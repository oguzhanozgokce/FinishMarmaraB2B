package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.PaymentContract
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMPaymentOptions(
    uiState: PaymentContract.UiState,
    onAction: (PaymentContract.UiAction) -> Unit,
    modifier: Modifier = Modifier,
    onCheckedSaveCard: (Boolean) -> Unit,
    selectedCard: CreditCart? = null,
    onSelectedCard: (CreditCart) -> Unit
) {
    var showNewCreditCart by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.cardBackground, shape = RoundedCornerShape(padding.dimension8))
            .border(
                padding.dimension1,
                colors.lightGray.copy(alpha = 0.4f),
                shape = RoundedCornerShape(padding.dimension8)
            )
            .padding(horizontal = padding.dimension16, vertical = padding.dimension16)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding.dimension8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(bottom = padding.dimension8),
                text = stringResource(R.string.card_information),
                style = FMTheme.typography.titleMediumMedium().copy(
                    fontSize = FMTheme.fontSize.mediumSmall
                )
            )
            Text(
                modifier = Modifier
                    .clickable { showNewCreditCart = !showNewCreditCart }
                    .padding(bottom = padding.dimension8),
                text = if (showNewCreditCart) {
                    stringResource(R.string.pay_with_another_card)
                } else {
                    stringResource(
                        R.string.pay_with_saved_cards
                    )
                },
                style = FMTheme.typography.titleMediumMedium().copy(
                    fontSize = FMTheme.fontSize.small,
                    color = colors.button
                )
            )
        }
        FMHorizontalDivider()
        AnimatedVisibility(
            visible = !showNewCreditCart,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = padding.dimension16),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(PreviewMockData.defaultCart) { card ->
                    FMCreditCard(
                        creditCard = card,
                        isSelected = selectedCard == card,
                        onSelected = { onSelectedCard(card) }
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = showNewCreditCart,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            FMNewCreditCart(
                uiState = uiState,
                onAction = onAction,
                onCheckedSaveCard = onCheckedSaveCard
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FMPaymentOptionsPreview() {
    FMTheme {
        FMPaymentOptions(
            uiState = PaymentContract.UiState(),
            onAction = {},
            onCheckedSaveCard = {},
            selectedCard = null,
            onSelectedCard = {}
        )
    }
}
