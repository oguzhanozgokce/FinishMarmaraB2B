package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ui.home.component.CategoryCardShimmer
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductListType
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

private const val SHIMMER_COUNT = 5

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: Category,
    onNavigateToCategory: (Int, String, ProductListType) -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .noRippleClickable {
                onNavigateToCategory(
                    category.id,
                    category.name,
                    ProductListType.CATEGORY
                )
            },
        shape = RoundedCornerShape(padding.dimension16),
        colors = CardDefaults.cardColors(
            containerColor = colors.cardBackground
        ),
        border = BorderStroke(padding.dimension1, colors.onBackground.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = padding.dimension12,
                vertical = padding.dimension8
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(padding.dimension36)
                    .background(color = colors.background, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (category.categoryImage.isNotEmpty()) {
                    AsyncImage(
                        model = category.categoryImage,
                        contentDescription = null,
                        modifier = Modifier.size(padding.dimension36),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = icons.notification,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
            Spacer(modifier = Modifier.width(padding.dimension12))
            Text(
                text = category.name,
                style = typography.bodyMediumNormal().copy(
                    color = colors.text
                ),
            )
        }
    }
}

@Composable
fun CategoryList(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    categoryList: LazyPagingItems<Category>?,
    onNavigateToCategory: (Int, String, ProductListType) -> Unit
) {
    val refreshState = categoryList?.loadState?.refresh
    val isInitialLoading = refreshState is LoadState.Loading

    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        if (isLoading || isInitialLoading) {
            items(SHIMMER_COUNT) {
                CategoryCardShimmer()
            }
        } else {
            categoryList?.let { lazyItems ->
                items(lazyItems.itemCount) { categoryItem ->
                    val categoryItem = lazyItems[categoryItem]
                    if (categoryItem != null) {
                        CategoryCard(
                            category = categoryItem,
                            modifier = modifier.padding(end = padding.dimension12),
                            onNavigateToCategory = onNavigateToCategory
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    CategoryCard(
        category = Category(
            id = 1,
            name = "Elektronik",
            categoryImage = "",
        ),
        onNavigateToCategory = { _, _, _ -> }
    )
}
