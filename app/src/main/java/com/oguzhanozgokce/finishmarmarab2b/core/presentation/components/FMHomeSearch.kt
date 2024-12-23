package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

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
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.poppinsFontFamily

@Composable
fun FMSearch(
    modifier: Modifier = Modifier,
    onNavigateToSearch: () -> Unit
) {
    val containerColor = colors.white
    val indicatorColor = colors.lightGray

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(padding.dimension20))
            .background(containerColor)
            .border(
                width = padding.dimension1,
                color = indicatorColor,
                shape = RoundedCornerShape(padding.dimension20)
            )
            .noRippleClickable { onNavigateToSearch() }
            .padding(padding.dimension16)
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
                        start = padding.dimension4,
                        end = padding.dimension8
                    ),
                tint = colors.black
            )
            Text(
                text = stringResource(id = R.string.search_for_products),
                color = colors.black.copy(alpha = 0.6f),
                fontFamily = poppinsFontFamily,
                fontSize = FMTheme.fontSize.medium,
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