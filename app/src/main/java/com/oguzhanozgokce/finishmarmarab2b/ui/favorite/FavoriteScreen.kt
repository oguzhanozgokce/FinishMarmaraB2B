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
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.components.CustomDivider
import com.oguzhanozgokce.finishmarmarab2b.ui.components.EmptyFavoriteScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.components.FMFavoriteList
import com.oguzhanozgokce.finishmarmarab2b.ui.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.dimensions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme.typography
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
        uiState.isLoading -> LoadingBar()
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
            .background(colors.white)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensions.sixteen,
                    end = dimensions.sixteen,
                    top = dimensions.sixteen
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(dimensions.fortyEight)
                    .clip(CircleShape)
                    .background(colors.semiTransparentWhite)
                    .padding(dimensions.eight),
                tint = Color.LightGray
            )
            Spacer(modifier = Modifier.width(dimensions.eight))
            Text(
                text = "Oguzhan Ozgokce",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = typography.medium,
                color = colors.black
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimensions.sixteen, end = dimensions.eight)
        ) {
            Text(
                text = "$0 Favorites",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = typography.small,
                color = colors.darkGray
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
                .padding(start = dimensions.eight, end = dimensions.eight),
            color = colors.semiTransparentWhite,
            thickness = dimensions.one
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