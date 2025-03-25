package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.limitDigits
import com.oguzhanozgokce.finishmarmarab2b.core.domain.util.CardNumberVisualTransformation.cardNumberVisualTransformation
import com.oguzhanozgokce.finishmarmarab2b.core.domain.util.CvvVisualTransformation
import com.oguzhanozgokce.finishmarmarab2b.core.domain.util.VisualTransformations.expirationDateTransformation
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlineTextField
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedButton
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder

private const val ARROW_SIZE = 10
private const val ARROW_POSITION = 0.5f
private const val BALLOON_PADDING = 12
private const val BALLOON_MARGIN_HORIZONTAL = 12
private const val BALLOON_CORNER_RADIUS = 8f
private const val INDICATORS_COLOR_ALPHA = 0.3f
private const val MAX_WEIGHT_04 = 0.4f
private const val LIMIT_DIGITS_4 = 4
private const val LIMIT_DIGITS_16 = 16

@Composable
fun AddCreditCardBottomSheetContent(
    uiState: CreditCardContract.UiState,
    onAction: (CreditCardContract.UiAction) -> Unit,
    onDismiss: () -> Unit,
    onAddCreditCard: () -> Unit,
) {
    val builder = rememberBalloonBuilder {
        setArrowSize(ARROW_SIZE)
        setArrowPosition(ARROW_POSITION)
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setPadding(BALLOON_PADDING)
        setMarginHorizontal(BALLOON_MARGIN_HORIZONTAL)
        setCornerRadius(BALLOON_CORNER_RADIUS)
        setBackgroundColorResource(R.color.gray)
        setBalloonAnimation(BalloonAnimation.ELASTIC)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colors.cardBackground)
            .padding(horizontal = padding.dimension16)
            .padding(bottom = padding.dimension16),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(padding.dimension8),
    ) {
        FMHorizontalDivider(
            modifier = Modifier
                .width(32.dp)
                .padding(vertical = 12.dp),
            thickness = 2.dp,
            color = colors.onBackground,
            paddingValues = PaddingValues(0.dp)
        )
        Text(
            text = "Create New Credit Card",
            style = FMTheme.typography.headMediumSemiBold(),
            modifier = Modifier.padding(vertical = padding.dimension8)
        )
        FMOutlineTextField(
            value = uiState.cardName,
            onValueChange = { onAction(CreditCardContract.UiAction.OnChangeCardName(it)) },
            modifier = Modifier.fillMaxWidth(),
            indicatorsColor = colors.text.copy(alpha = INDICATORS_COLOR_ALPHA),
            label = stringResource(R.string.card_name),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        FMOutlineTextField(
            value = uiState.cardNumber,
            onValueChange = { newValue ->
                onAction(
                    CreditCardContract.UiAction.OnChangeCardNumber(
                        newValue.limitDigits(
                            LIMIT_DIGITS_16
                        )
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
            indicatorsColor = colors.text.copy(alpha = INDICATORS_COLOR_ALPHA),
            label = stringResource(R.string.card_number),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            visualTransformation = cardNumberVisualTransformation
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.weight(MAX_WEIGHT_04)
            ) {
                FMOutlineTextField(
                    value = uiState.expirationDate,
                    onValueChange = {
                        onAction(
                            CreditCardContract.UiAction.OnChangeExpirationDate(
                                it.limitDigits(
                                    LIMIT_DIGITS_4
                                )
                            )
                        )
                    },
                    visualTransformation = expirationDateTransformation,
                    modifier = Modifier.fillMaxWidth(),
                    indicatorsColor = colors.text.copy(alpha = INDICATORS_COLOR_ALPHA),
                    label = stringResource(R.string.expiration_date)
                )
            }
            Spacer(modifier = Modifier.width(padding.dimension16))
            Column(
                modifier = Modifier.weight(MAX_WEIGHT_04)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FMOutlineTextField(
                        value = uiState.cvv,
                        onValueChange = { onAction(CreditCardContract.UiAction.OnChangeCvv(it)) },
                        visualTransformation = CvvVisualTransformation(),
                        modifier = Modifier.weight(1f),
                        indicatorsColor = colors.text.copy(alpha = INDICATORS_COLOR_ALPHA),
                        label = stringResource(R.string.cvv),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        trailingIcon = {
                            Balloon(
                                builder = builder,
                                balloonContent = {
                                    Text(
                                        text = stringResource(R.string.cvv_info),
                                        style = FMTheme.typography.bodySmallNormal()
                                    )
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
        FMOutlineTextField(
            value = uiState.cardTitle,
            onValueChange = { onAction(CreditCardContract.UiAction.OnChangeCardTitle(it)) },
            modifier = Modifier.fillMaxWidth(),
            indicatorsColor = colors.text.copy(alpha = INDICATORS_COLOR_ALPHA),
            label = stringResource(R.string.card_title),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrectEnabled = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding.dimension8),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension16)
        ) {
            FMOutlinedButton(
                onClick = onDismiss,
                text = "Cancel",
                modifier = Modifier.weight(1f),
                height = padding.dimension48,
                backgroundColor = colors.cardBackground,
                shape = RoundedCornerShape(padding.dimension16),
                contentPadding = PaddingValues(0.dp)
            )
            FMButton(
                onClick = { onAddCreditCard() },
                text = "Add",
                height = padding.dimension48,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(padding.dimension16),
                contentPadding = PaddingValues(0.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun AddCreditCardBottomSheetContentPreview() {
    FMTheme {
        AddCreditCardBottomSheetContent(
            uiState = CreditCardContract.UiState(),
            onAction = { },
            onDismiss = { },
            onAddCreditCard = { }
        )
    }
}
