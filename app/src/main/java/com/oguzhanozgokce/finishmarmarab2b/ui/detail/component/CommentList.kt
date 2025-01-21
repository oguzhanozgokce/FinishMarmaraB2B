package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment

@Composable
fun CommentList(
    comments: LazyPagingItems<UserComment>?,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        comments?.let { lazyItems ->
            items(lazyItems.itemCount) { index ->
                val commentItem = lazyItems[index]
                if (commentItem != null) {
                    CommentItem(
                        comment = commentItem
                    )
                }
            }
        }
    }
}