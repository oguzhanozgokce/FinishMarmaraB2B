package com.oguzhanozgokce.finishmarmarab2b.ui.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.search.HistorySearch
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun HistorySection(
    historyList: List<HistorySearch>,
    onClearAllClick: () -> Unit = {},
    onHistoryItemClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colors.cardBackground)
            .padding(horizontal = padding.dimension16, vertical = padding.dimension8)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colors.cardBackground),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.history_searches),
                style = typography.headMediumSemiBold()
            )
            TextButton(
                onClick = { onClearAllClick() },
                modifier = Modifier.wrapContentSize(),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.clear_all),
                    style = typography.headMediumSemiBold().copy(
                        color = colors.primary,
                        fontSize = fontSize.mediumSmall
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(padding.dimension8))
        HistoryItemList(
            historyList = historyList,
            onHistoryItemClick = onHistoryItemClick
        )
    }
}

@PreviewLightDark
@Composable
fun HistorySectionPreview() {
    FMTheme {
        HistorySection(
            historyList = PreviewMockData.historyList,
            onClearAllClick = { },
            onHistoryItemClick = { }
        )
    }
}
