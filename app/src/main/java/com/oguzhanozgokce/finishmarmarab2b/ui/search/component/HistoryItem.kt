package com.oguzhanozgokce.finishmarmarab2b.ui.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlinedCard
import com.oguzhanozgokce.finishmarmarab2b.ui.search.HistorySearch
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun HistoryItem(
    historySearch: HistorySearch,
    modifier: Modifier = Modifier,
    onHistoryItemClick: (String) -> Unit = {}
) {
    FMOutlinedCard(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = padding.dimension4),
        shape = RoundedCornerShape(padding.dimension4),
        cardColors = CardDefaults.cardColors(
            containerColor = colors.cardBackground
        ),
        content = {
            Row(
                modifier = Modifier.padding(
                    vertical = padding.dimension4,
                    horizontal = padding.dimension8
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(padding.dimension4)
            ) {
                Text(
                    text = historySearch.text,
                    style = typography.bodySmallLight().copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = fontSize.mediumSmall
                    )
                )
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = colors.primary,
                    modifier = Modifier
                        .size(24.dp)
                        .noRippleClickable {
                            onHistoryItemClick(historySearch.text)
                        }
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
fun HistoryItemPreview() {
    FMTheme {
        Row {
            HistoryItem(
                historySearch = HistorySearch(
                    text = "Iphone 13"
                )
            )
            Spacer(modifier = Modifier.width(padding.dimension8))
            HistoryItem(
                historySearch = HistorySearch(
                    text = "Headphones"
                )
            )
        }
    }
}
