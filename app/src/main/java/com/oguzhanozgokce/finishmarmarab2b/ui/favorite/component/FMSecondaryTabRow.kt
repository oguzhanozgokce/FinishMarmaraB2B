package com.oguzhanozgokce.finishmarmarab2b.ui.favorite.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.oguzhanozgokce.finishmarmarab2b.ui.favorite.model.PrimaryTab
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors

private const val ANIMATION_DURATION_MS = 300
private const val SLIDE_OFFSET_DIVISOR = 4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FMSecondaryTabRow(
    modifier: Modifier = Modifier,
    tabs: List<PrimaryTab> = emptyList(),
    selectedTabIndex: Int = 0,
    onSelectTab: (Int) -> Unit = {},
    indicatorColor: Color = colors.primary,
    tabContent: @Composable (Int) -> Unit
) {
    Column(modifier = modifier) {
        SecondaryTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = modifier,
            tabs = {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { onSelectTab(index) },
                        text = {
                            Text(
                                text = tab.title,
                                color = if (selectedTabIndex == index) colors.primary else colors.onBackground
                            )
                        },
                        icon = tab.icon?.let {
                            {
                                Icon(
                                    imageVector = it,
                                    contentDescription = null,
                                    tint = if (selectedTabIndex == index) colors.primary else colors.onBackground
                                )
                            }
                        }
                    )
                }
            },
            containerColor = colors.cardBackground,
            contentColor = colors.onBackground,
            indicator = {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(selectedTabIndex),
                    color = indicatorColor
                )
            },
        )
        AnimatedContent(
            targetState = selectedTabIndex,
            transitionSpec = {
                fadeIn(animationSpec = tween(ANIMATION_DURATION_MS)) +
                    slideInHorizontally(initialOffsetX = { it / SLIDE_OFFSET_DIVISOR }) togetherWith
                    fadeOut(animationSpec = tween(ANIMATION_DURATION_MS)) +
                    slideOutHorizontally(targetOffsetX = { -it / SLIDE_OFFSET_DIVISOR })
            },
            label = "SharedAxis"
        ) { tabIndex ->
            tabContent(tabIndex)
        }
    }
}
