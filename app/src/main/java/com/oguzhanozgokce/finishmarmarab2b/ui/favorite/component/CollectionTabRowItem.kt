package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMDropdownMenu
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMDropdownMenuItem
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun CollectionTabRowItem(
    collections: Collection,
    modifier: Modifier = Modifier,
    onEditCollection: (Int) -> Unit = {},
    onDeleteCollection: (Int) -> Unit = {},
) {
    var menuExpanded by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        FMCard(
            modifier = Modifier
                .fillMaxWidth()
                .size(padding.dimension160, padding.dimension180)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            menuExpanded = true
                        }
                    )
                },
            cardColors = CardDefaults.cardColors(
                containerColor = colors.cardBackground,
            ),
            shape = RoundedCornerShape(padding.dimension8),
            border = BorderStroke(
                width = padding.dimension1,
                color = colors.primary.copy(alpha = 0.2f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colors.cardBackground),
            ) {
                LazyRow(
                    modifier = modifier
                        .fillMaxSize()
                        .weight(1f),
                    state = rememberLazyListState(),
                    reverseLayout = false,
                    horizontalArrangement = Arrangement.spacedBy(padding.dimension8),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    items(collections.imageList) { imageProduct ->
                        AsyncImage(
                            model = imageProduct,
                            modifier = Modifier
                                .size(
                                    padding.dimension140,
                                    padding.dimension160
                                )
                                .padding(start = padding.dimension4),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.height(padding.dimension4))
                FMHorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = padding.dimension4)
                )
                Spacer(modifier = Modifier.height(padding.dimension4))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = "${collections.name} (${collections.productCount})",
                        style = FMTheme.typography.titleSmallMedium().copy(
                            fontSize = FMTheme.fontSize.mediumSmall
                        ),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(
                                start = padding.dimension8
                            )
                    )
                }
            }
        }
        FMDropdownMenu(
            expanded = menuExpanded,
            offset = DpOffset(0.dp, (-100).dp),
            onDismissRequest = { menuExpanded = false },
            menuItems = listOf(
                FMDropdownMenuItem(
                    text = "Edit",
                    icon = Icons.Default.Edit,
                    onClick = {
                        menuExpanded = false
                        onEditCollection(collections.id)
                    }
                ),
                FMDropdownMenuItem(
                    text = "Delete",
                    icon = Icons.Default.Delete,
                    onClick = {
                        menuExpanded = false
                        onDeleteCollection(collections.id)
                    }
                )
            )
        )
    }
}

@PreviewLightDark
@Composable
fun CollectionTabRowItemPreview() {
    FMTheme {
        val collections = PreviewMockData.defaultCollection
        CollectionTabRowItem(
            collections = collections,
            modifier = Modifier,
            onDeleteCollection = {}
        )
    }
}
