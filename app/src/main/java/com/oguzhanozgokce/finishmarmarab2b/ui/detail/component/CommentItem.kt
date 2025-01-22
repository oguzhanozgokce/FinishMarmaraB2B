package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMAnnotatedText
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCard
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.RatingBar
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun CommentItem(
    comment: UserComment,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(170.dp)
            .width(300.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = colors.background)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RatingBar(
                rating = comment.rating,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = comment.date,
                style = typography.bodySmallLight(),
            )
        }
        Text(
            text = comment.userName,
            style = typography.bodySmallLight(),
            modifier = Modifier.padding(top = padding.dimension4)
        )
        FMCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(75.dp),
            content = {
                Text(
                    text = comment.comment,
                    style = typography.titleSmallMedium(),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(padding.dimension8)
                )
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        FMAnnotatedText(
            prefix = "Seller",
            highlightedText = comment.sellerName,
            modifier = Modifier.padding(bottom = padding.dimension4),
            style = typography.bodySmallLight(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommentItemPreview() {
    FMTheme {
        CommentItem(comment = PreviewMockData.defaultUserComment)
    }
}
