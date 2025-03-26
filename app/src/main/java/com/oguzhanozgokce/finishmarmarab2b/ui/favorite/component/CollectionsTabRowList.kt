package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun CollectionsTabRowList(
    collectionsList: List<Collection>,
    modifier: Modifier = Modifier,
    onDeleteCollection: (Int) -> Unit,
    onEditCollection: (Int, String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(padding.dimension8),
        horizontalArrangement = Arrangement.spacedBy(padding.dimension8),
        verticalArrangement = Arrangement.spacedBy(padding.dimension8)
    ) {
        itemsIndexed(collectionsList) { index, collections ->
            Column {
                CollectionTabRowItem(
                    collections = collections,
                    modifier = Modifier.fillMaxWidth(),
                    onDeleteCollection = { onDeleteCollection(collections.id) },
                    onEditCollection = onEditCollection
                )
                if (index == collectionsList.lastIndex) {
                    Spacer(modifier = Modifier.height(72.dp))
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun CollectionsTabRowListPreview() {
    FMTheme {
        val collectionsList = PreviewMockData.defaultCollectionList
        CollectionsTabRowList(
            collectionsList = collectionsList,
            modifier = Modifier,
            onDeleteCollection = { },
            onEditCollection = { _, _ -> }
        )
    }
}
