package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CardType
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.maskCreditCardNumber
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.toIconRes
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreditCardItem(
    creditCard: CreditCart,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
) {
    FMCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        cardColors = CardDefaults.cardColors(
            containerColor = FMTheme.colors.primary.copy(alpha = 0.85f)
        ),
        shape = RoundedCornerShape(FMTheme.padding.dimension16),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = FMTheme.colors.primary.copy(alpha = 0.85f),
                )
                .padding(FMTheme.padding.dimension16)
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 8.dp)
                    .background(
                        color = FMTheme.colors.onPrimary.copy(alpha = 0.05f),
                        shape = CircleShape
                    )
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = creditCard.cardTitle,
                        style = FMTheme.typography.titleMediumMedium(),
                        color = FMTheme.colors.onPrimary
                    )
                    Image(
                        painter = painterResource(id = R.drawable.chip),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }

                Spacer(modifier = Modifier.height(FMTheme.padding.dimension24))

                Text(
                    text = maskCreditCardNumber(creditCard.cardNumber),
                    style = FMTheme.typography.titleLargeBold().copy(
                        fontSize = FMTheme.fontSize.extraLarge,
                        color = FMTheme.colors.onPrimary
                    ),
                    letterSpacing = 2.sp,
                )

                Spacer(modifier = Modifier.height(FMTheme.padding.dimension16))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Text(
                            text = "Valid Until",
                            style = FMTheme.typography.titleSmallMedium(),
                            color = FMTheme.colors.onPrimary.copy(alpha = 0.7f)
                        )
                        Text(
                            text = creditCard.lastDate,
                            style = FMTheme.typography.titleMediumMedium(),
                            color = FMTheme.colors.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.width(FMTheme.padding.dimension24))

                    Column {
                        Text(
                            text = "CVV",
                            style = FMTheme.typography.titleSmallMedium(),
                            color = FMTheme.colors.onPrimary.copy(alpha = 0.7f)
                        )
                        Text(
                            text = creditCard.cardCvv,
                            style = FMTheme.typography.titleMediumMedium(),
                            color = FMTheme.colors.onPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(FMTheme.padding.dimension16))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = "Card Holder",
                            style = FMTheme.typography.titleSmallMedium(),
                            color = FMTheme.colors.onPrimary.copy(alpha = 0.7f)
                        )
                        Text(
                            text = creditCard.cardNameSurname,
                            style = FMTheme.typography.titleMediumMedium(),
                            color = FMTheme.colors.onPrimary
                        )
                    }

                    Image(
                        painter = painterResource(id = creditCard.cardType.toIconRes()),
                        contentDescription = stringResource(
                            when (creditCard.cardType) {
                                CardType.VISA -> R.string.card_type_visa
                                CardType.MASTERCARD -> R.string.card_type_mastercard
                            }
                        ),
                        modifier = Modifier
                            .size(FMTheme.padding.dimension48)
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun CreditCardItemPreview() {
    FMTheme {
        CreditCardItem(
            creditCard = PreviewMockData.defaultMasterCart
        )
    }
}
