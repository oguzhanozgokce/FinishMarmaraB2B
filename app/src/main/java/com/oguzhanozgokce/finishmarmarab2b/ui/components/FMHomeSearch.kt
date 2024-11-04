package com.oguzhanozgokce.finishmarmarab2b.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.common.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme

@Composable
fun CustomHomeSearch(
    modifier: Modifier = Modifier,
    onNavigateToSearch: () -> Unit
) {
    val containerColor = MB2BTheme.colors.white
    val indicatorColor = MB2BTheme.colors.primary.copy(alpha = 0.3f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(MB2BTheme.dimensions.eight))
            .noRippleClickable {
                onNavigateToSearch()
            }
            .background(containerColor)
            .border(
                MB2BTheme.dimensions.two,
                indicatorColor,
                RoundedCornerShape(MB2BTheme.dimensions.eight)
            )
            .padding(MB2BTheme.dimensions.sixteen)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_icon),
                modifier = Modifier
                    .padding(
                        start = MB2BTheme.dimensions.four,
                        end = MB2BTheme.dimensions.eight
                    )
            )
            Text(
                text = stringResource(id = R.string.search_for_products),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomHomeSearchPreview() {
    CustomHomeSearch(
        modifier = Modifier,
        onNavigateToSearch = {}
    )
}