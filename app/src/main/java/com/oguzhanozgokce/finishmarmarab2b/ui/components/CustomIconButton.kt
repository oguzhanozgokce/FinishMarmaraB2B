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
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.LMTheme

@Composable
fun CustomIconButton(
    iconResId: Int,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClickAction,
        modifier = modifier
            .size(LMTheme.dimensions.fortyEight)
            .border(
                BorderStroke(LMTheme.dimensions.one, LMTheme.colors.primary),
                shape = RoundedCornerShape(LMTheme.dimensions.twelve)
            )
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomIconButtonPreview() {
    CustomIconButton(iconResId = 0, onClickAction = {})
}
