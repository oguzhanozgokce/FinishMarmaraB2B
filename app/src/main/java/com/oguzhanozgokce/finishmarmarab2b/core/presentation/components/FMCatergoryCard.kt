package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

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
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.shimmer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Category
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: Category,
    onNavigateToCategory: (Int, String) -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .noRippleClickable { onNavigateToCategory(category.id, category.name) },
        shape = RoundedCornerShape(padding.dimension16),
        colors = CardDefaults.cardColors(
            containerColor = colors.white
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = padding.dimension16,
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
                    color = colors.black
                ),
            )
        }
    }
}

@Composable
fun CategoryList(
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    categoryList: LazyPagingItems<Category>?,
    onNavigateToCategory: (Int, String) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        if (isLoading) {
            items(5) {
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

@Composable
fun CategoryCardShimmer(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .shimmer(isLoading = true),
        shape = RoundedCornerShape(padding.dimension24),
        colors = CardDefaults.cardColors(
            containerColor = colors.white
        )
    ) {
        Text(
            text = "Shimmer",
            style = typography.bodyMediumNormal().copy(
                color = colors.black
            ),
        )
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
        onNavigateToCategory = { _, _ -> }
    )
}
