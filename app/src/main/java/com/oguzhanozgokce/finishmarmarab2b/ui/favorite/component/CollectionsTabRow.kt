package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Collection
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

@Composable
fun CollectionsTabRow(
    collectionsList: List<Collection>,
    onCreateCollectionClick: () -> Unit,
    onDeleteCollectionClick: (Int) -> Unit,
    onEditCollectionClick: (Int, String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        CollectionsTabRowList(
            collectionsList = collectionsList,
            modifier = Modifier
                .fillMaxSize(),
            onDeleteCollection = onDeleteCollectionClick,
            onEditCollection = onEditCollectionClick,
        )

        FMButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            height = 48.dp,
            text = "Create Collection",
            onClick = { onCreateCollectionClick() },
            shape = CircleShape,
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 8.dp)
        )
    }
}
