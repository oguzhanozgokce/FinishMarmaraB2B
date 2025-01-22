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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.model.CategoryUi
import com.oguzhanozgokce.finishmarmarab2b.ui.home.HomeContract
import com.oguzhanozgokce.finishmarmarab2b.ui.home.sampleCategoryList
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.icons
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    categoryUi: CategoryUi
) {
    Card(
        modifier = modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(padding.dimension24),
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
                Icon(
                    imageVector = icons.notification,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(padding.dimension12))
            Text(
                text = categoryUi.name,
                style = typography.bodyMediumNormal().copy(
                    color = colors.black
                ),
            )
        }
    }
}

@Composable
fun CategoryList(
    uiState: HomeContract.UiState,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(sampleCategoryList) { categoryItem ->
            CategoryCard(
                categoryUi = categoryItem,
                modifier = modifier.padding(end = padding.dimension12)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryListPreview() {
    CategoryList(
        uiState = HomeContract.UiState(
            categoryList = sampleCategoryList
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    CategoryCard(
        categoryUi = CategoryUi(
            id = 1,
            name = "Elektronik",
            image = R.drawable.ic_notification,
        )
    )
}
