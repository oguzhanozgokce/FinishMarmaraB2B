package com.oguzhanozgokce.finishmarmarab2b.ui.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun CartTopBar(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = colors.white)
            .padding(horizontal = padding.dimension16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "My Cart",
                style = typography.titleMediumMedium(),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "(15 product)",
                style = typography.bodySmallNormal(),
                color = colors.text.copy(alpha = 0.6f)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onDeleteClick) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Delete,
                contentDescription = "Clear Basket",
                tint = colors.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartTopBarPreview() {
    FMTheme {
        CartTopBar()
    }
}
