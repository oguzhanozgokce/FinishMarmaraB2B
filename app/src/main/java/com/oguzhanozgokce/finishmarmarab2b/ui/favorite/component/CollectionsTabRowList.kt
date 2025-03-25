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
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun CollectionsTabRowList(
    collectionsList: List<Collection>,
    modifier: Modifier = Modifier
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
                    modifier = Modifier.fillMaxWidth()
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
        val collectionsList = listOf(
            Collection(
                id = 1,
                productCount = 10,
                name = "Example Collection",
                imageList = listOf(
                    "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                    "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                    "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93",
                ),
            ),
            Collection(
                id = 2,
                productCount = 15,
                name = "Example Collection 2",
                imageList = listOf(
                    "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                    "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                    "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93",
                ),
            ),
            Collection(
                id = 2,
                productCount = 15,
                name = "Example Collection 2",
                imageList = listOf(
                    "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                    "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                    "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93",
                ),
            )
        )
        CollectionsTabRowList(
            collectionsList = collectionsList,
            modifier = Modifier
        )
    }
}
