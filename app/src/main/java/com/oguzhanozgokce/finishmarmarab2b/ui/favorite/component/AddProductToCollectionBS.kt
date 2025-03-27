package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedButton
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.FavoriteContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun AddProductToCollectionBS(
    uiState: UiState,
    onDismissRequest: () -> Unit,
    onAddProductToCollection: (Collection) -> Unit,
    onSaveProductToCollection: () -> Unit
) {
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
            text = "Add Product to Collection",
            style = FMTheme.typography.headMediumSemiBold(),
            modifier = Modifier.padding(vertical = padding.dimension8)
        )
        Spacer(modifier = Modifier.height(padding.dimension8))
        CollectionInfoItemList(
            collections = uiState.collectionList,
            modifier = Modifier.padding(horizontal = padding.dimension8),
            onCollectionClick = { collection -> onAddProductToCollection(collection) },
            selectedCollection = uiState.selectedCollection
        )
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
                onClick = { onSaveProductToCollection() },
                text = "Save",
                height = padding.dimension48,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(padding.dimension16),
                contentPadding = PaddingValues(0.dp)
            )
        }
    }
}

@Composable
fun CollectionInfoItem(
    collection: Collection,
    modifier: Modifier = Modifier,
    onCollectionClick: (Collection) -> Unit,
    isSelected: Boolean,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) colors.primary else colors.onBackground.copy(alpha = 0.2f),
                shape = RoundedCornerShape(padding.dimension16)
            )
            .background(
                color = colors.cardBackground,
                shape = RoundedCornerShape(16.dp)
            )
            .noRippleClickable {
                onCollectionClick(collection)
            }
            .padding(padding.dimension8),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = collection.imageList.firstOrNull(),
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(shape = CircleShape),
            contentDescription = collection.name,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = collection.name,
            style = FMTheme.typography.titleMediumMedium().copy(
                color = if (isSelected) colors.primary else colors.text,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CollectionInfoItemList(
    collections: List<Collection>,
    modifier: Modifier = Modifier,
    selectedCollection: Collection?,
    onCollectionClick: (Collection) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(collections) { collection ->
            CollectionInfoItem(
                collection = collection,
                isSelected = selectedCollection?.id == collection.id,
                onCollectionClick = { onCollectionClick(collection) }
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CollectionInfoItemPreview() {
    FMTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.background),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            CollectionInfoItem(
                collection = PreviewMockData.defaultCollection,
                isSelected = true,
                onCollectionClick = { },
                modifier = Modifier
            )
            CollectionInfoItem(
                collection = PreviewMockData.defaultCollection,
                isSelected = false,
                onCollectionClick = { },
                modifier = Modifier
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CollectionInfoItemListPreview() {
    FMTheme {
        CollectionInfoItemList(
            collections = PreviewMockData.defaultCollectionList,
            modifier = Modifier,
            selectedCollection = PreviewMockData.defaultCollection,
            onCollectionClick = { }
        )
    }
}