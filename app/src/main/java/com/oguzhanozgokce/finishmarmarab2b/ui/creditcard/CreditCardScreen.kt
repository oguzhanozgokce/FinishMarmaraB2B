package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMIcon
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMTopBar
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CardType
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.CreditCardContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.maskCreditCardNumber
import com.oguzhanozgokce.finishmarmarab2b.ui.payment.component.toIconRes
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CreditCardScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navAction: NavAction,
) {
    val context = LocalContext.current
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> context.showToast(effect.message)
        }
    }

    when {
        uiState.isLoading -> LoadingBar()
        else -> CreditCardContent(
            uiState = uiState,
            navAction = navAction,
        )
    }
}

@Composable
fun CreditCardContent(
    uiState: UiState,
    navAction: NavAction
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            FMTopBar(
                title = " Credit Cards ",
                onNavigationClick = { navAction.navigateToBack() },
                actions = {
                    FMIcon(
                        modifier = Modifier.padding(end = FMTheme.padding.dimension16),
                        backgroundColor = FMTheme.colors.primary.copy(alpha = 0.3f),
                        boxSize = FMTheme.padding.dimension36,
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = FMTheme.colors.onBackground,
                            )
                        }
                    )
                },
                containerColor = FMTheme.colors.cardBackground,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FMTheme.colors.background)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CreditCardList(
                creditCards = uiState.creditCardList,
                modifier = Modifier,
                onItemClick = { },
                onItemLongClick = { }
            )
        }
    }
}

@Composable
fun CreditCardList(
    creditCards: List<CreditCart>,
    modifier: Modifier = Modifier,
    onItemClick: (CreditCart) -> Unit = {},
    onItemLongClick: (CreditCart) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            items = creditCards,
            key = { it.id }
        ) { creditCard ->
            CreditCardItem(
                creditCard = creditCard,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onItemClick(creditCard) },
                onLongClick = { onItemLongClick(creditCard) }
            )
        }
    }
}

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
            .padding(horizontal = 16.dp, vertical = 8.dp)
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
                    style = FMTheme.typography.titleLargeBold(),
                    color = FMTheme.colors.onPrimary,
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

@PreviewLightDark
@Composable
fun CreditCardScreenPreview(
    @PreviewParameter(CreditCardScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        CreditCardScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            navAction = NavAction(
                navigateToBack = {}
            )
        )
    }
}
