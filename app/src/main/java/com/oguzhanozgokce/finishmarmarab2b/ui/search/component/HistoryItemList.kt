package com.oguzhanozgokce.finishmarmarab2b.ui.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.oguzhanozgokce.finishmarmarab2b.ui.search.HistorySearch
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun HistoryItemList(
    historyList: List<HistorySearch>,
    onHistoryItemClick: (String) -> Unit = {}
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
    ) {
        items(historyList) { historySearch ->
            HistoryItem(historySearch = historySearch, onHistoryItemClick = onHistoryItemClick)
        }
    }
}
