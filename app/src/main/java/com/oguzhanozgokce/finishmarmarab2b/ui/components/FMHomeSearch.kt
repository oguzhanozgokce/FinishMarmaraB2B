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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun FMSearch(
    modifier: Modifier = Modifier,
    onNavigateToSearch: () -> Unit
) {
    val containerColor = LMTheme.colors.searchBarColor
    val indicatorColor = LMTheme.colors.white

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(LMTheme.dimensions.twenty))
            .background(containerColor)
            .border(
                width = LMTheme.dimensions.one,
                color = indicatorColor,
                shape = RoundedCornerShape(LMTheme.dimensions.twenty)
            )
            .noRippleClickable { onNavigateToSearch() }
            .padding(LMTheme.dimensions.sixteen)
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
                        start = LMTheme.dimensions.four,
                        end = LMTheme.dimensions.eight
                    ),
                tint = LMTheme.colors.searchIconColor
            )
            Text(
                text = stringResource(id = R.string.search_for_products),
                color = LMTheme.colors.darkGray.copy(alpha = 0.6f),
                fontFamily = poppinsFontFamily,
                fontSize = LMTheme.typography.medium,
                fontWeight = FontWeight.Light,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomHomeSearchPreview() {
    FMSearch(
        modifier = Modifier,
        onNavigateToSearch = {}
    )
}