package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMIcon
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CardType
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@DrawableRes
fun CardType.toIconRes(): Int {
    return when (this) {
        CardType.VISA -> R.drawable.ic_visa_logo
        CardType.MASTERCARD -> R.drawable.ic_mastercard
    }
}

private const val FLIPPED_ROTATION = 180f
private const val DEFAULT_ROTATION = 0f
private const val ROTATION_THRESHOLD = 90f
private const val ANIMATION_DURATION = 600
private const val BORDER_ALPHA = 0.2f
private const val ICON_ALPHA = 0.5f
private const val CARD_BACKGROUND_ALPHA = 0.1f
private const val SPACER_WEIGHT = 1f
private const val CAMERA_DISTANCE_MULTIPLIER = 30f
private const val TRANSFORM_ORIGIN_X = 0.5f
private const val TRANSFORM_ORIGIN_Y = 0.5f
private const val CARD_NUMBER_LENGTH = 16
private const val VISIBLE_DIGITS = 4
private const val MASKED_SECTION = " **** "

@Composable
fun FMCreditCard(
    creditCard: CreditCart,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    var isFlipped by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isFlipped) FLIPPED_ROTATION else DEFAULT_ROTATION,
        animationSpec = tween(durationMillis = ANIMATION_DURATION)
    )
    val cardBackgroundColor = colors.lightGray.copy(alpha = CARD_BACKGROUND_ALPHA)
    val iconBackgroundColor =
        if (isSelected) colors.primary.copy(alpha = ICON_ALPHA) else colors.primary.copy(alpha = BORDER_ALPHA)
    Box(
        modifier = Modifier
            .width(250.dp)
            .height(150.dp)
            .background(color = colors.cardBackground)
            .border(
                width = if (isSelected) padding.dimension2 else padding.dimension1,
                color = if (isSelected) colors.primary else colors.primary.copy(alpha = BORDER_ALPHA),
                shape = RoundedCornerShape(padding.dimension16)
            )
            .graphicsLayer {
                rotationY = rotationAngle
                transformOrigin = TransformOrigin(TRANSFORM_ORIGIN_X, TRANSFORM_ORIGIN_Y)
                cameraDistance = CAMERA_DISTANCE_MULTIPLIER * density
            }
            .noRippleClickable { onSelected() }
    ) {
        if (rotationAngle <= ROTATION_THRESHOLD) {
            FrontSideCreditCard(
                creditCard,
                cardBackgroundColor = cardBackgroundColor,
                borderColor = iconBackgroundColor,
                onFlip = { isFlipped = !isFlipped },
            )
        } else {
            BackSideCreditCard(
                creditCard = creditCard,
                cardBackgroundColor = cardBackgroundColor,
                borderColor = iconBackgroundColor,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationY = FLIPPED_ROTATION
                        transformOrigin = TransformOrigin(TRANSFORM_ORIGIN_X, TRANSFORM_ORIGIN_Y)
                        cameraDistance = CAMERA_DISTANCE_MULTIPLIER * density
                    },
                onFlip = { isFlipped = !isFlipped }
            )
        }
    }
}

@Composable
fun FrontSideCreditCard(
    creditCard: CreditCart,
    modifier: Modifier = Modifier,
    cardBackgroundColor: Color = colors.lightGray.copy(alpha = CARD_BACKGROUND_ALPHA),
    borderColor: Color,
    onFlip: () -> Unit
) {
    FMCard(
        modifier = modifier.background(
            color = cardBackgroundColor,
            shape = RoundedCornerShape(padding.dimension16)
        ),
        cardColors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = padding.dimension8,
                    horizontal = padding.dimension12
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding.dimension4)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = creditCard.cardTitle,
                        style = typography.titleMediumSemiBold()
                    )
                    FMIcon(
                        onClick = { onFlip() },
                        backgroundColor = borderColor,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_360),
                                contentDescription = null,
                            )
                        },
                        boxSize = padding.dimension36,
                    )
                }
                Text(
                    text = maskCreditCardNumber(creditCard.cardNumber),
                    style = typography.titleMediumMedium().copy(
                        fontSize = FMTheme.fontSize.mediumSmall
                    )
                )
                Spacer(modifier = Modifier.weight(SPACER_WEIGHT))
                Image(
                    painter = painterResource(id = creditCard.cardType.toIconRes()),
                    contentDescription = stringResource(
                        when (creditCard.cardType) {
                            CardType.VISA -> R.string.card_type_visa
                            CardType.MASTERCARD -> R.string.card_type_mastercard
                        }
                    ),
                    modifier = Modifier
                        .size(padding.dimension48)
                        .align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun BackSideCreditCard(
    creditCard: CreditCart,
    modifier: Modifier = Modifier,
    cardBackgroundColor: Color = colors.lightGray.copy(alpha = CARD_BACKGROUND_ALPHA),
    borderColor: Color,
    onFlip: () -> Unit
) {
    FMCard(
        modifier = modifier.background(
            color = cardBackgroundColor,
            shape = RoundedCornerShape(padding.dimension16)
        ),
        cardColors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = padding.dimension8, horizontal = padding.dimension12)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding.dimension4)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = creditCard.cardTitle,
                        style = typography.titleMediumMedium()
                    )
                    FMIcon(
                        onClick = { onFlip() },
                        backgroundColor = borderColor,
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_360),
                                contentDescription = null,
                            )
                        },
                        boxSize = padding.dimension36,
                    )
                }
                Spacer(modifier = Modifier.height(padding.dimension8))
                Text(
                    text = maskCreditCardNumber(creditCard.cardNumber),
                    style = typography.titleMediumMedium()
                )
                Spacer(modifier = Modifier.height(padding.dimension4))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(padding.dimension24),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = creditCard.lastDate,
                        style = typography.titleSmallMedium()
                    )
                    Text(
                        text = creditCard.cardCvv,
                        style = typography.titleSmallMedium()
                    )
                }
                Spacer(modifier = Modifier.weight(SPACER_WEIGHT))
                Image(
                    painter = painterResource(id = creditCard.cardType.toIconRes()),
                    contentDescription = stringResource(
                        when (creditCard.cardType) {
                            CardType.VISA -> R.string.card_type_visa
                            CardType.MASTERCARD -> R.string.card_type_mastercard
                        }
                    ),
                    modifier = Modifier
                        .size(padding.dimension48)
                        .align(Alignment.End)
                )
            }
        }
    }
}

fun maskCreditCardNumber(creditCardNumber: String): String {
    return if (creditCardNumber.length == CARD_NUMBER_LENGTH) {
        creditCardNumber.take(VISIBLE_DIGITS) + MASKED_SECTION + creditCardNumber.takeLast(
            VISIBLE_DIGITS
        )
    } else {
        creditCardNumber
    }
}

@PreviewLightDark
@Composable
fun FrontSideCreditCardPreview() {
    FMTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            BackSideCreditCard(
                creditCard = PreviewMockData.defaultVisaCart,
                modifier = Modifier
                    .padding(padding.dimension16)
                    .size(250.dp, 150.dp),
                borderColor = colors.primary.copy(alpha = BORDER_ALPHA),
                onFlip = {}
            )
            FrontSideCreditCard(
                creditCard = PreviewMockData.defaultMasterCart,
                modifier = Modifier
                    .padding(padding.dimension16)
                    .size(250.dp, 150.dp),
                cardBackgroundColor = colors.cardBackground,
                borderColor = colors.primary.copy(alpha = BORDER_ALPHA),
                onFlip = {}
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding.dimension16),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(PreviewMockData.defaultCart) {
                    FMCreditCard(
                        creditCard = it,
                        isSelected = false,
                        onSelected = {}
                    )
                }
            }
        }
    }
}
