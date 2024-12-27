package com.oguzhanozgokce.finishmarmarab2b.ui.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.getInitials
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileContract
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun UserImage(
    uiState: ProfileContract.UiState
) {
    Box(
        modifier = Modifier
            .size(padding.dimension80)
            .padding(padding.dimension8)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(colors.primary, colors.white)
                ),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = uiState.user.getInitials(),
            style = FMTheme.typography.headLargeBold().copy(
                color = colors.white,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserImagePreview() {
    FMTheme {
        UserImage(
            uiState = ProfileContract.UiState()
        )
    }
}
