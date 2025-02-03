package com.oguzhanozgokce.finishmarmarab2b.ui.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyImageScreen
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun ImageList(
    modifier: Modifier = Modifier,
    imageList: List<String>
) {
    if (imageList.isNotEmpty()) {
        val pagerState = rememberPagerState(pageCount = { imageList.size })
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(colors.white)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                pageContent = { page ->
                    AsyncImage(
                        model = imageList[page],
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .background(colors.lightGray.copy(alpha = 0.2f), shape = CircleShape)
                    .padding(horizontal = padding.dimension8, vertical = padding.dimension2)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(imageList.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .size(padding.dimension12)
                            .clip(CircleShape)
                            .background(if (isSelected) colors.primary else colors.lightGray)
                            .padding(horizontal = 4.dp)
                    )
                }
            }
        }
    } else {
        EmptyImageScreen(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(colors.red)
                .clip(RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 32.dp),
            message = "No images found for this product",
            iconSize = 120.dp,
            textColor = colors.text,
            iconTint = colors.cardBackground
        )
    }
}
