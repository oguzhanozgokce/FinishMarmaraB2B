package com.oguzhanozgokce.finishmarmarab2b.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyImageScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMBackIcon
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalPager
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FavoriteButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun DetailScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.product.id == 0 -> DetailContent(uiState = uiState)
        else -> {
            DetailContent(uiState = uiState)
        }
    }
}

@Composable
fun DetailContent(
    uiState: UiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(padding.dimension16),
    ) {
        TopBarDetail()
        ImageList(
            uiState = uiState
        )
        Spacer(modifier = Modifier.height(padding.dimension16))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = uiState.product.name,
                fontSize = fontSize.medium,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = colors.primary
            )
            Text(
                text = uiState.product.sellerName,
                fontSize = fontSize.medium,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = colors.black
            )

        }
        Spacer(modifier = Modifier.height(padding.dimension8))
    }
}

@Composable
fun ImageList(
    modifier: Modifier = Modifier,
    uiState: UiState
) {
    val imageList = uiState.product.getImageList().filterNotNull()
    if (imageList.isNotEmpty()) {
        FMHorizontalPager(
            imageList = imageList,
            modifier = modifier
                .fillMaxWidth()
                .background(colors.black)
                .padding(horizontal = 16.dp)
        )
    } else {
        EmptyImageScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            message = "No images found for this product",
            iconSize = 120.dp,
            textColor = colors.text,
            iconTint = colors.cardBackground
        )

    }
}

@Composable
fun TopBarDetail(
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = padding.dimension8)
    ) {
        FMBackIcon(modifier = Modifier.align(Alignment.CenterStart))
        Text(
            text = "Detail Screen",
            fontSize = fontSize.body,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = colors.black,
            modifier = Modifier.align(Alignment.Center)
        )
        FavoriteButton(
            backgroundColor = colors.cardBackground,
            boxSize = padding.dimension48,
            modifier = Modifier.align(Alignment.CenterEnd),
            isFavorite = false,
            onToggleClick = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(
    @PreviewParameter(DetailScreenPreviewProvider::class) uiState: UiState,
) {
    DetailScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}