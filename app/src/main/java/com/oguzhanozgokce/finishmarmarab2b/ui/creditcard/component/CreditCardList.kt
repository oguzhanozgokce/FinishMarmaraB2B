package com.oguzhanozgokce.finishmarmarab2b.ui.creditcard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.CreditCart

@Composable
fun CreditCardList(
    creditCards: List<CreditCart>,
    modifier: Modifier = Modifier,
    onItemClick: (CreditCart) -> Unit = {},
    onItemLongClick: (CreditCart) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            items = creditCards,
            key = { it.id }
        ) { creditCard ->
            CreditCardItem(
                creditCard = creditCard,
                modifier = Modifier.fillMaxWidth(),
                onClick = { onItemClick(creditCard) },
                onLongClick = { onItemLongClick(creditCard) }
            )
        }
    }
}
