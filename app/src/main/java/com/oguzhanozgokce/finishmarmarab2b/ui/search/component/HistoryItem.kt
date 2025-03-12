package com.oguzhanozgokce.finishmarmarab2b.ui.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedCard
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.SearchHistory
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductListType
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun HistoryItem(
    searchHistory: SearchHistory,
    modifier: Modifier = Modifier,
    onHistoryItemClick: (String, ProductListType) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    FMOutlinedCard(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = padding.dimension4),
        shape = RoundedCornerShape(padding.dimension4),
        cardColors = CardDefaults.cardColors(
            containerColor = colors.cardBackground
        ),
        content = {
            Row(
                modifier = Modifier
                    .padding(
                        vertical = padding.dimension4,
                        horizontal = padding.dimension8
                    )
                    .noRippleClickable {
                        onHistoryItemClick(
                            searchHistory.searchHistory,
                            ProductListType.SEARCH
                        )
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
            ) {
                Text(
                    text = searchHistory.searchHistory,
                    style = typography.bodySmallLight().copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = fontSize.mediumSmall
                    )
                )
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .noRippleClickable { onDeleteClick(searchHistory.id) },
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = colors.primary,
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
fun HistoryItemPreview() {
    FMTheme {
        Row {
            HistoryItem(
                searchHistory = PreviewMockData.defaultSearchHistory,
                onHistoryItemClick = { _, _ -> },
                onDeleteClick = {}
            )
            Spacer(modifier = Modifier.width(padding.dimension8))
            HistoryItem(
                searchHistory = PreviewMockData.defaultLongSearchHistory,
                onHistoryItemClick = { _, _ -> },
                onDeleteClick = {}
            )
        }
    }
}
