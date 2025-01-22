package com.oguzhanozgokce.finishmarmarab2b.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.QuestionAnswer
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.UserComment
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.DetailContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.component.BottomDetail
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.component.ImageList
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.component.ProductDescriptionSection
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.component.ProductDetailHeader
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.component.ProductEvaluationSection
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.component.ProductQuestionsSection
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.component.SelectedSellerSection
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.component.SellerSection
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.component.TopBarDetail
import com.oguzhanozgokce.finishmarmarab2b.ui.detail.navigation.DetailNavActions
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun DetailScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navActions: DetailNavActions
) {
    val context = LocalContext.current
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.ShowToast -> {
                context.showToast(effect.message)
            }
        }
    }

    val commentItem = uiState.comments?.collectAsLazyPagingItems()
    val questionAnswers = uiState.questionsAndAnswers?.collectAsLazyPagingItems()

    when {
        uiState.isLoading -> LoadingBar()
        else -> {
            DetailContent(
                uiState = uiState,
                commentItem = commentItem,
                questionAnswers = questionAnswers,
                navActions = navActions
            )
        }
    }
}

@Composable
fun DetailContent(
    uiState: UiState,
    commentItem: LazyPagingItems<UserComment>?,
    questionAnswers: LazyPagingItems<QuestionAnswer>?,
    navActions: DetailNavActions
) {
    Scaffold(
        topBar = {
            TopBarDetail(
                onBackClick = navActions.navigateToBack,
                onCartClick = navActions.navigateToCart,
                onSearchClick = navActions.navigateToSearch
            )
        },
        bottomBar = {
            uiState.product?.let { product ->
                BottomDetail(
                    product = product,
                    onAddToCart = { },
                    onNowAddToCart = { }
                )
            }
        },
        content = { innerPadding ->
            uiState.product?.let { product ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors.background)
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .background(colors.white)
                            .padding(padding.dimension16)
                    ) {
                        ImageList(imageList = product.imageUrl)
                        Spacer(modifier = Modifier.height(padding.dimension16))
                        ProductDetailHeader(product = product)
                        Spacer(modifier = Modifier.height(padding.dimension4))
                        SelectedSellerSection(
                            sellerName = product.seller?.name,
                            onQuestionsAndAnswersClick = { }
                        )
                    }
                    Spacer(modifier = Modifier.height(padding.dimension8))
                    ProductDescriptionSection(product = product)
                    Spacer(modifier = Modifier.height(padding.dimension8))
                    SellerSection(
                        sellerName = product.seller?.name,
                        sellerImageUrl = product.sellerImageUrl,
                        followerCount = 15600,
                        onFollowClick = { }
                    )
                    Spacer(modifier = Modifier.height(padding.dimension8))
                    ProductQuestionsSection(
                        questionAnswers = questionAnswers,
                        onSeeAllClick = { }
                    )
                    Spacer(modifier = Modifier.height(padding.dimension8))
                    ProductEvaluationSection(
                        rate = product.rate,
                        commentCount = product.commentCount,
                        comments = commentItem,
                        onSeeAllClick = {}
                    )
                    Spacer(modifier = Modifier.height(padding.dimension8))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(
    @PreviewParameter(DetailScreenPreviewProvider::class) uiState: UiState,
) {
    FMTheme {
        DetailScreen(
            uiState = uiState,
            uiEffect = emptyFlow(),
            onAction = {},
            navActions = DetailNavActions(
                navigateToBack = {},
                navigateToCart = {},
                navigateToSearch = {}
            )
        )
    }
}
