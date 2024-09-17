package com.oguzhanozgokce.finishmarmarab2b.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.MB2BTheme

@Composable
fun CustomIconButton(
    iconResId: Int,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClickAction,
        modifier = modifier
            .size(MB2BTheme.dimensions.fortyEight)
            .border(
                BorderStroke(MB2BTheme.dimensions.one, MB2BTheme.colors.primary),
                shape = RoundedCornerShape(MB2BTheme.dimensions.twelve)
            )
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null
        )
    }
}
