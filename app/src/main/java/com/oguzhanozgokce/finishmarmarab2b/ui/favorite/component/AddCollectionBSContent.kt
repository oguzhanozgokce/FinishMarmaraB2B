package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlineTextField
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedButton
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddCollectionBSContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    onDismissRequest: () -> Unit,
    onAddCollection: (String) -> Unit
) {
    val suggestions = listOf(
        "Favoriler",
        "Özel Koleksiyon",
        "Satın Alınacaklar",
        "İncelenecekler",
        "Sezonluk Ürünler"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colors.cardBackground)
            .padding(horizontal = padding.dimension16)
            .padding(bottom = padding.dimension16),
        horizontalAlignment = Alignment.CenterHorizontally
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
            text = "Create New Collection",
            style = FMTheme.typography.headMediumSemiBold(),
            modifier = Modifier.padding(vertical = padding.dimension8)
        )
        Spacer(modifier = Modifier.height(padding.dimension8))
        FMOutlineTextField(
            value = uiState.collectionName,
            onValueChange = { onAction(UiAction.OnChangeCollectionName(it)) },
            label = "Collection Name",
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(padding.dimension16))
        Text(
            text = "Suggested Names",
            style = FMTheme.typography.titleMediumMedium(),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = padding.dimension8)
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension8),
            verticalArrangement = Arrangement.spacedBy(padding.dimension4)
        ) {
            suggestions.forEach { suggestion ->
                SuggestionChip(
                    onClick = { uiState.collectionName = suggestion },
                    label = { Text(suggestion) },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = colors.primary.copy(alpha = 0.05f),
                        labelColor = colors.primary
                    ),
                    border = BorderStroke(width = 1.dp, color = colors.primary)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding.dimension16),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension16)
        ) {
            FMOutlinedButton(
                onClick = onDismissRequest,
                text = "Cancel",
                modifier = Modifier.weight(1f),
                height = padding.dimension48,
                backgroundColor = colors.cardBackground,
                shape = RoundedCornerShape(padding.dimension16),
                contentPadding = PaddingValues(0.dp)
            )
            FMButton(
                onClick = {
                    if (uiState.collectionName.isNotBlank()) {
                        onAddCollection(uiState.collectionName)
                    }
                },
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
fun AddCollectionBottomSheetContentPreview() {
    FMTheme {
        AddCollectionBSContent(
            uiState = UiState(),
            onAction = {},
            onDismissRequest = {},
            onAddCollection = {}
        )
    }
}
