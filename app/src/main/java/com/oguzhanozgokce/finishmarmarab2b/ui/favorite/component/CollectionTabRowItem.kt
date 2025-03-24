package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun CollectionTabRowItem(
    collections: Collection,
    modifier: Modifier = Modifier,
) {
    FMCard(
        modifier = modifier.size(padding.dimension160, padding.dimension180),
        cardColors = CardDefaults.cardColors(
            containerColor = colors.cardBackground,
        ),
        shape = RoundedCornerShape(padding.dimension8),
        border = BorderStroke(width = padding.dimension1, color = colors.primary.copy(alpha = 0.2f))
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
}

@PreviewLightDark
@Composable
fun CollectionTabRowItemPreview() {
    FMTheme {
        val collections = Collection(
            id = 1,
            productCount = 10,
            name = "Example Collection",
            imageList = listOf(
                "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg",
                "https://cdn.dsmcdn.com/ty1534/product/media/images/prod/PIM/20240910/09/e5cbc3fb-0d07-403c-93d8-e634aec88d16/1_org_zoom.jpg"
            )
        )
        CollectionTabRowItem(
            collections = collections,
            modifier = Modifier
        )
    }
}
