package com.oguzhanozgokce.finishmarmarab2b.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.CustomDivider
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyFavoriteScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMFavoriteList
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun FavoriteScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
//        uiState.isLoading -> LoadingBar()
        uiState.favoriteList.isEmpty() -> EmptyFavoriteScreen()
        else -> FavoriteContent(
            uiState = uiState,
        )
    }
}

@Composable
fun FavoriteContent(
    uiState: UiState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = padding.dimension16,
                    end = padding.dimension16,
                    top = padding.dimension16
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(padding.dimension48)
                    .clip(CircleShape)
                    .background(colors.cardBackground)
                    .padding(padding.dimension8),
                tint = Color.LightGray
            )
            Spacer(modifier = Modifier.width(padding.dimension8))
            Text(
                text = "Oguzhan Ozgokce",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = fontSize.medium,
                color = colors.text
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding.dimension16, end = padding.dimension8)
        ) {
            Text(
                text = "$0 Favorites",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = fontSize.small,
                color = colors.text
            )
            IconButton(onClick = { /* Menü İşlemleri */ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu",
                    tint = Color.Black
                )
            }
        }
        CustomDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = padding.dimension8, end = padding.dimension8),
            color = colors.black,
            thickness = padding.dimension1
        )
        FMFavoriteList(
            uiState = uiState,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview(
    @PreviewParameter(FavoriteScreenPreviewProvider::class) uiState: UiState,
) {
    FavoriteScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}
