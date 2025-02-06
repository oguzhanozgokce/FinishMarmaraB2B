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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMIcon
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

enum class CardType(
    val displayName: String,
    @DrawableRes val iconRes: Int
) {
    VISA("Visa", R.drawable.ic_visa_logo),
    MASTERCARD("Mastercard", R.drawable.ic_mastercard),
}

data class CreditCard(
    val cardTitle: String,
    val cardNumber: String,
    val cardHolder: String,
    val expirationDate: String,
    val cvv: String,
    val cardType: CardType,
)

@Composable
fun FMCreditCard(
    creditCard: CreditCard,
) {
    var isFlipped by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 600)
    )
    val cardBackgroundColor = when (creditCard.cardType) {
        CardType.VISA -> Color(0xFFE3F2FD)
        CardType.MASTERCARD -> Color(0xFFFFF3E0)
    }
    val borderColor = when (creditCard.cardType) {
        CardType.VISA -> Color(0xFFBBDEFB)
        CardType.MASTERCARD -> Color(0xFFFFE0B2)
    }
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(180.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .graphicsLayer {
                rotationY = rotationAngle
                transformOrigin = TransformOrigin(0.5f, 0.5f)
                cameraDistance = 30f * density
            }
    ) {
        if (rotationAngle <= 90f) {
            FrontSideCreditCard(
                creditCard,
                cardBackgroundColor = cardBackgroundColor,
                borderColor = borderColor,
                onFlip = { isFlipped = !isFlipped },
            )
        } else {
            BackSideCreditCard(
                creditCard = creditCard,
                cardBackgroundColor = cardBackgroundColor,
                borderColor = borderColor,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationY = 180f
                        transformOrigin = TransformOrigin(0.5f, 0.5f)
                        cameraDistance = 30f * density
                    },
                onFlip = { isFlipped = !isFlipped }
            )
        }
    }
}

@Composable
fun FrontSideCreditCard(
    creditCard: CreditCard,
    modifier: Modifier = Modifier,
    cardBackgroundColor: Color,
    borderColor: Color,
    onFlip: () -> Unit
) {
    FMCard(
        modifier = modifier.background(
            color = cardBackgroundColor,
            shape = RoundedCornerShape(16.dp)
        ),
        cardColors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding.dimension16)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = padding.dimension4)
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
                Spacer(modifier = Modifier.weight(1f))
                when (creditCard.cardType) {
                    CardType.VISA -> {
                        Image(
                            painter = painterResource(id = R.drawable.ic_visa_logo),
                            contentDescription = null,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    CardType.MASTERCARD -> {
                        Image(
                            painter = painterResource(id = R.drawable.ic_mastercard),
                            contentDescription = null,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BackSideCreditCard(
    creditCard: CreditCard,
    modifier: Modifier = Modifier,
    cardBackgroundColor: Color,
    borderColor: Color,
    onFlip: () -> Unit
) {
    FMCard(
        modifier = modifier.background(
            color = cardBackgroundColor,
            shape = RoundedCornerShape(16.dp)
        ),
        cardColors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding.dimension16)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = padding.dimension8)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = creditCard.cardHolder,
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
                        text = creditCard.expirationDate,
                        style = typography.titleSmallMedium()
                    )
                    Text(
                        text = creditCard.cvv,
                        style = typography.titleSmallMedium()
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                when (creditCard.cardType) {
                    CardType.VISA -> {
                        Image(
                            painter = painterResource(id = R.drawable.ic_visa_logo),
                            contentDescription = null,
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    CardType.MASTERCARD -> {
                        Image(
                            painter = painterResource(id = R.drawable.ic_mastercard),
                            contentDescription = null,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }
    }
}

fun maskCreditCardNumber(creditCardNumber: String): String {
    return if (creditCardNumber.length == 16) {
        creditCardNumber.take(4) + " **** " + creditCardNumber.takeLast(4)
    } else {
        creditCardNumber
    }
}

fun creditCardFormat(creditCardNumber: String): String {
    val digits = creditCardNumber.filter { it.isDigit() }
    val formatted = StringBuilder()
    for (i in digits.indices) {
        if (i % 4 == 0 && i != 0) {
            formatted.append(" ")
        }
        formatted.append(digits[i])
    }
    return formatted.toString()
}

@Preview(showBackground = true, backgroundColor = 0xFFFfffff)
@Composable
fun FrontSideCreditCardPreview() {
    FMTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            BackSideCreditCard(
                creditCard = CreditCard(
                    cardTitle = "Test",
                    cardNumber = "5367890123456436",
                    cardHolder = "John Doe",
                    expirationDate = "12/25",
                    cvv = "123",
                    cardType = CardType.VISA
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .size(200.dp, 200.dp),
                cardBackgroundColor = Color(0xFFE3F2FD),
                borderColor = Color(0xFFBBDEFB),
                onFlip = {}
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(sampleCreditCardList) {
                    FMCreditCard(creditCard = it)
                }
            }
        }
    }
}

val sampleCreditCardList = listOf(
    CreditCard(
        cardTitle = "Test",
        cardNumber = "5367890123456436",
        cardHolder = "John Doe",
        expirationDate = "12/25",
        cvv = "123",
        cardType = CardType.MASTERCARD
    ),
    CreditCard(
        cardTitle = "Test",
        cardNumber = "4367890123456436",
        cardHolder = "Jane Smith",
        expirationDate = "11/26",
        cvv = "456",
        cardType = CardType.VISA
    ),
)
