package com.oguzhanozgokce.finishmarmarab2b.ui.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun UserNameSurname(
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .padding(padding.dimension8)
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Oğuzhan",
                style = FMTheme.typography.headMediumSemiBold(),
            )
            Spacer(modifier = Modifier.width(padding.dimension8))
            Text(
                text = "Özgökce",
                style = FMTheme.typography.headMediumSemiBold(),
            )
        }
        Text(
            text = "ozgokceoguzhan34@gmail.com",
            style = FMTheme.typography.headSmallMedium().copy(
                color = Color.DarkGray,
            ),
        )
    }
}
