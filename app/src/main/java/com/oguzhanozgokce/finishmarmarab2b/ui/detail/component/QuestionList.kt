package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.mockLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

@Composable
fun QuestionList(
    questionAnswers: LazyPagingItems<QuestionAnswer>?,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        questionAnswers?.let { lazyItems ->
            items(lazyItems.itemCount) { index ->
                val questionAnswerItem = lazyItems[index]
                if (questionAnswerItem != null) {
                    QuestionItem(
                        questionAnswer = questionAnswerItem,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionListPreview() {
    val mockData = listOf(
        QuestionAnswer(
            id = 1,
            question = "What is the return policy?",
            answer = "You can return it within 30 days.",
            userName = "John Doe",
            sellerName = "BestSeller",
            sellerImageUrl = "https://upload.wikimedia.org/wikipedia/tr/7/70/Marmara_%C3%9Cniversitesi_logo.png",
            date = "2025-01-20"
        ),
        QuestionAnswer(
            id = 2,
            question = "Is it compatible with iPhone?",
            answer = "Yes, it works perfectly with iPhone.",
            userName = "Jane Smith",
            sellerName = "TopSeller",
            sellerImageUrl = "https://upload.wikimedia.org/wikipedia/tr/7/70/Marmara_%C3%9Cniversitesi_logo.png",
            date = "2025-01-21"
        )
    )
    val lazyPagingItems = mockLazyPagingItems(mockData)

    FMTheme {
        QuestionList(
            questionAnswers = lazyPagingItems
        )
    }
}

