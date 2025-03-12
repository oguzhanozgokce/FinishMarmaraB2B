package com.oguzhanozgokce.finishmarmarab2b.ui.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.SearchHistory
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductListType
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun HistoryItemList(
    searchHistoryList: List<SearchHistory>,
    onHistoryItemClick: (String, ProductListType) -> Unit,
    onDeleteClick: (Int) -> Unit,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
    ) {
        items(searchHistoryList) { searchHistory ->
            HistoryItem(
                searchHistory = searchHistory,
                onHistoryItemClick = onHistoryItemClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@PreviewLightDark
@Composable
fun HistoryItemListPreview() {
    FMTheme {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
        ) {
            items(PreviewMockData.historyList) {
                HistoryItem(
                    searchHistory = it,
                    onHistoryItemClick = { _, _ -> },
                    onDeleteClick = {}
                )
            }
        }
    }
}
