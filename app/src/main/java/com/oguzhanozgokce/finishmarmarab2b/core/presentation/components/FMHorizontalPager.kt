package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding

@Composable
fun FMHorizontalPager(
    modifier: Modifier = Modifier,
    imageList: List<Any>,
    pagerHeight: Dp = 200.dp,
    cornerRadius: Dp = padding.dimension16,
    activeIndicatorColor: Color = colors.red,
    inactiveIndicatorColor: Color = colors.cardBackground,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { imageList.size }
    )
    Box(
        modifier = modifier
            .height(pagerHeight)
            .fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius)),
            pageSpacing = padding.dimension8
        ) { page ->
            val painter = rememberAsyncImagePainter(model = imageList[page])
            Image(
                painter = painter,
                contentDescription = "Image $page",
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.cardBackground),
                contentScale = ContentScale.Crop
            )
        }

        val currentPage by remember {
            derivedStateOf { pagerState.currentPage }
        }

        HorizontalPagerIndicator(
            pageCount = imageList.size,
            currentPage = currentPage,
            activeColor = activeIndicatorColor,
            inactiveColor = inactiveIndicatorColor,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = padding.dimension16)
        )
    }
}

@Composable
fun HorizontalPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = colors.lightGray,
    inactiveColor: Color = colors.black,
    indicatorSize: Dp = padding.dimension8,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val color = if (index == currentPage) activeColor else inactiveColor
            Box(
                modifier = Modifier
                    .size(indicatorSize)
                    .background(color, shape = CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FMHorizontalPagerPreview() {
    val imageList = listOf(
        R.drawable.image_product,
        R.drawable.image_product,
        R.drawable.image_product,
    )
    FMTheme {
        FMHorizontalPager(imageList = imageList)
    }
}
