package com.oguzhanozgokce.finishmarmarab2b.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CategoryList
import com.oguzhanozgokce.finishmarmarab2b.ui.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.components.FMSearch
import com.oguzhanozgokce.finishmarmarab2b.ui.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.components.ProductList
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.dimensions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.typography
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun HomeScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.categoryList.isEmpty() -> EmptyScreen()
        uiState.productList.isEmpty() -> EmptyScreen()
        else -> HomeContent(
            uiState = uiState,
        )
    }
}

@Composable
fun HomeContent(
    uiState: UiState,
) {
    Column(
        modifier = Modifier
            .padding(dimensions.sixteen)
            .fillMaxSize()
            .background(colors.white)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensions.twelve)
    ) {
        TopBar()
        FMSearch(
            modifier = Modifier,
            onNavigateToSearch = {}
        )
        SaleCard(modifier = Modifier.fillMaxWidth())
        Text(
            text = stringResource(id = R.string.categories),
            fontSize = typography.body,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFontFamily,
            color = colors.darkBlue
        )
        CategoryList(
            modifier = Modifier.padding(start = dimensions.six),
            uiState = uiState
        )
        ProductText()
        ProductList(
            uiState = uiState
        )
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Hello,",
                fontFamily = poppinsFontFamily,
                fontSize = typography.medium,
                fontWeight = FontWeight.Light,
                color = colors.darkGray
            )
            Text(
                text = "Oguzhan O.",
                fontFamily = poppinsFontFamily,
                fontSize = typography.large,
                fontWeight = FontWeight.ExtraBold,
                color = colors.darkGray
            )
        }
        Icon(
            imageVector = LMTheme.icons.notification,
            contentDescription = null,
            tint = colors.darkGray,
            modifier = Modifier
                .padding(dimensions.sixteen)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun SaleCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(dimensions.eight),
        colors = CardDefaults.cardColors(
            containerColor = colors.searchIconColor
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimensions.sixteen,
                    vertical = dimensions.eight
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SUMMER SALE",
                fontSize = typography.large,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily,
                color = colors.white
            )
            Text(
                text = "50% OFF",
                fontSize = typography.extraLarge,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = poppinsFontFamily,
                color = colors.white
            )
            Text(
                text = "Amazing discounts on selected items! Don't miss out!",
                fontSize = typography.small,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily,
                color = colors.white
            )
            TextButton(
                onClick = { /* TODO: Navigate to the sale page */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Explore Now",
                        fontSize = typography.medium,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily,
                        color = colors.white,
                        modifier = Modifier.padding(bottom = dimensions.four)
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .width(dimensions.oneHundred)
                            .height(dimensions.two),
                        thickness = dimensions.one,
                        color = colors.white
                    )
                }
            }
        }
    }
}

@Composable
fun ProductText(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.products),
            fontSize = typography.body,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFontFamily,
            color = colors.darkBlue
        )
        TextButton(
            onClick = { /* TODO: Navigate to all products */ },
        ) {
            Column {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "See all",
                    fontSize = typography.medium,
                    fontWeight = FontWeight.Light,
                    fontFamily = poppinsFontFamily,
                    color = colors.darkBlue
                )
                HorizontalDivider(
                    modifier = Modifier
                        .width(dimensions.sixty)
                        .height(dimensions.two),
                    thickness = dimensions.one,
                    color = colors.darkGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(
    @PreviewParameter(HomeScreenPreviewProvider::class) uiState: UiState,
) {
    HomeScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}