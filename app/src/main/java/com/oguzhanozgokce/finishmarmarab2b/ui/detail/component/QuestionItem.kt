package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@Composable
fun QuestionItem(
    questionAnswer: QuestionAnswer,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(170.dp)
            .width(300.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = colors.background)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = questionAnswer.userName,
                style = typography.bodySmallLight(),
            )
            Text(
                text = questionAnswer.date,
                style = typography.bodySmallLight(),
                textAlign = TextAlign.End
            )
        }
        Text(
            text = questionAnswer.question,
            style = typography.titleSmallMedium(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(90.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = colors.white
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = padding.dimension4)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = questionAnswer.sellerImageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(padding.dimension24)
                            .clip(CircleShape)
                            .border(
                                width = padding.dimension1,
                                color = colors.lightGray,
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = questionAnswer.sellerName,
                        style = typography.titleSmallMedium().copy(
                            fontSize = fontSize.mediumSmall
                        ),
                    )
                }
                Text(
                    text = questionAnswer.answer,
                    style = typography.titleSmallMedium(),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = padding.dimension4),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuestionItemPreview() {
    FMTheme {
        QuestionItem(
            questionAnswer = PreviewMockData.defaultQuestionAnswer,
            modifier = Modifier
        )
    }
}
