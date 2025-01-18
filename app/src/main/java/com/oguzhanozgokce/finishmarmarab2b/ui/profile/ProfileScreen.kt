package com.oguzhanozgokce.finishmarmarab2b.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMNotification
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.ProfileContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.component.ProfileCard
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.component.ProfileItem
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.component.UserImage
import com.oguzhanozgokce.finishmarmarab2b.ui.profile.component.UserNameSurname
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ProfileScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> ProfileContent(uiState = uiState)
    }
}

@Composable
fun ProfileContent(
    uiState: UiState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding.dimension16)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserImage(uiState = uiState)
                UserNameSurname(uiState = uiState)
                Spacer(modifier = Modifier.weight(1f))
                FMNotification()
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = padding.dimension8),
                color = colors.primary.copy(alpha = 0.2f),
                thickness = padding.dimension1
            )
            ProfileCard {
                ProfileItem(text = "My Orders", onClick = {})
                ProfileItem(text = "Favourites", onClick = {})
                ProfileItem(text = "Edit Profile", onClick = {})
                ProfileItem(text = "Change Password", onClick = {})
            }
            ProfileCard {
                ProfileItem(text = "Terms & Conditions", onClick = {})
                ProfileItem(text = "Privacy Policy", onClick = {})
            }
            ProfileCard {
                ProfileItem(text = "Help & Support", onClick = {})
                ProfileItem(text = "Feedback", onClick = {})
            }
            ProfileCard {
                ProfileItem(text = "Log Out", onClick = {})
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(
    @PreviewParameter(ProfileScreenPreviewProvider::class) uiState: UiState,
) {
    ProfileScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
    )
}