package com.oguzhanozgokce.finishmarmarab2b.ui.evaluation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.Product
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun EvaluationBottomDetail(
    product: Product,
    onAddToCart: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.cardBackground)
            .padding(end = padding.dimension8),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Question & Answer(1023)",
            style = FMTheme.typography.titleSmallMedium(),
            modifier = Modifier
                .weight(1f)
                .padding(padding.dimension16),
        )
        Spacer(modifier = Modifier.width(padding.dimension8))
        FMButton(
            text = "Add to basket",
            onClick = { onAddToCart(product.id) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = padding.dimension4),
            height = padding.dimension48
        )
    }
}

@PreviewLightDark
@Composable
fun EvaluationContentPreview() {
    FMTheme {
        EvaluationBottomDetail(
            product = PreviewMockData.defaultProduct,
            onAddToCart = {}
        )
    }
}
