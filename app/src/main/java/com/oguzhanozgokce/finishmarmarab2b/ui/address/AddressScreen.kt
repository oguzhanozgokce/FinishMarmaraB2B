package com.oguzhanozgokce.finishmarmarab2b.ui.address

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.R
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMCitiesBottomSheetContent
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMClickableOutlinedRow
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlineTextField
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMPhoneTextField
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMProvincesBottomSheetContent
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    uiState: AddressContract.UiState,
    uiEffect: Flow<AddressContract.UiEffect>,
    onAction: (AddressContract.UiAction) -> Unit,
    navAction: AddressNavAction
) {
    var showProvincesBottomSheet by rememberSaveable { mutableStateOf(false) }
    var showCitiesBottomSheet by rememberSaveable { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val context = LocalContext.current

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is AddressContract.UiEffect.ShowToast -> context.showToast(effect.message)
            is AddressContract.UiEffect.NavigateToPayment -> navAction.navigateToPayment()
        }
    }

    if (showProvincesBottomSheet) {
        FMProvincesBottomSheetContent(
            sheetState = state,
            onDismissRequest = { showProvincesBottomSheet = false },
            provinces = uiState.provinces,
            selectedProvince = uiState.selectedProvince,
            onProvinceSelected = { selectedProvince ->
                showProvincesBottomSheet = false
                onAction(AddressContract.UiAction.ProvinceSelected(selectedProvince))
            }
        )
    }

    if (showCitiesBottomSheet) {
        FMCitiesBottomSheetContent(
            sheetState = state,
            onDismissRequest = { showCitiesBottomSheet = false },
            selectedProvince = uiState.selectedProvince,
            selectedCity = uiState.selectedCity,
            onCitySelected = { selectedCity ->
                showCitiesBottomSheet = false
                onAction(AddressContract.UiAction.CitySelected(selectedCity))
            }
        )
    }

    AddressScreenContent(
        uiState = uiState,
        onNavigationCitiesBottomSheet = {
            onAction(AddressContract.UiAction.LoadProvinces)
            showProvincesBottomSheet = true
        },
        onNavigationDistrictsBottomSheet = {
            onAction(AddressContract.UiAction.LoadCities)
            showCitiesBottomSheet = true
        },
        navAction = navAction,
        onAction = onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreenContent(
    uiState: AddressContract.UiState,
    onNavigationCitiesBottomSheet: () -> Unit,
    onNavigationDistrictsBottomSheet: () -> Unit,
    navAction: AddressNavAction,
    onAction: (AddressContract.UiAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        Surface(
            shadowElevation = 4.dp
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.add_new_address),
                        style = typography.titleMediumMedium()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navAction.navigateToBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colors.white,
                    titleContentColor = colors.black
                ),
                windowInsets = WindowInsets(0.dp)
            )
        }
        Spacer(modifier = Modifier.height(padding.dimension8))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.primary.copy(alpha = 0.1f))
                .clip(RoundedCornerShape(padding.dimension8))
                .padding(padding.dimension8),
            verticalAlignment = Alignment.Top,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = stringResource(R.string.info),
                modifier = Modifier
                    .padding(start = padding.dimension4)
                    .size(padding.dimension16),
                tint = colors.primary
            )
            Spacer(modifier = Modifier.width(padding.dimension8))
            Text(
                text = stringResource(R.string.address_usage_info),
                style = typography.bodySmallNormal().copy(
                    color = colors.primary.copy(alpha = 0.7f)
                )
            )
        }
        Spacer(modifier = Modifier.height(padding.dimension8))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.white),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = padding.dimension16,
                    vertical = padding.dimension8
                ),
                text = stringResource(R.string.contact_details),
                style = typography.titleMediumMedium()
            )
            FMHorizontalDivider(modifier = Modifier.padding(horizontal = padding.dimension16))
            Spacer(modifier = Modifier.height(padding.dimension8))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = padding.dimension16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.name),
                    modifier = Modifier.size(padding.dimension32)
                )
                Spacer(modifier = Modifier.width(padding.dimension8))
                FMOutlineTextField(
                    value = uiState.addressName,
                    onValueChange = { onAction(AddressContract.UiAction.OnNameChanged(it)) },
                    label = stringResource(R.string.name),
                    indicatorsColor = colors.text.copy(alpha = 0.3f),
                )
            }
            Spacer(modifier = Modifier.height(padding.dimension8))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = padding.dimension16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(padding.dimension40))
                FMOutlineTextField(
                    value = uiState.addressSurname,
                    onValueChange = { onAction(AddressContract.UiAction.OnSurnameChanged(it)) },
                    label = stringResource(R.string.surname),
                    indicatorsColor = colors.text.copy(alpha = 0.3f),
                )
            }
            Spacer(modifier = Modifier.height(padding.dimension8))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = padding.dimension16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = stringResource(R.string.phone),
                    modifier = Modifier.size(padding.dimension32)
                )
                Spacer(modifier = Modifier.width(padding.dimension8))
                FMPhoneTextField(
                    phoneNumber = uiState.addressTel,
                    onPhoneNumberChange = {
                        onAction(
                            AddressContract.UiAction.OnPhoneNumberChanged(
                                it
                            )
                        )
                    },
                    isError = false,
                    errorMessage = null
                )
            }

            Spacer(modifier = Modifier.height(padding.dimension16))
        }
        Spacer(modifier = Modifier.height(padding.dimension8))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.white),
        ) {
            Text(
                text = stringResource(R.string.address_details),
                style = typography.titleMediumMedium(),
                modifier = Modifier
                    .padding(horizontal = padding.dimension16, vertical = padding.dimension8)
            )
            FMHorizontalDivider(modifier = Modifier.padding(horizontal = padding.dimension16))
            Spacer(modifier = Modifier.height(padding.dimension8))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.white)
                    .padding(horizontal = padding.dimension16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = stringResource(R.string.province),
                    modifier = Modifier.size(padding.dimension32)
                )
                Spacer(modifier = Modifier.width(padding.dimension8))
                FMClickableOutlinedRow(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.province),
                    value = uiState.selectedProvince?.name ?: "",
                    icon = Icons.Default.ArrowDropDown,
                    indicatorsColor = colors.text.copy(alpha = 0.3f),
                    onClick = { onNavigationCitiesBottomSheet() }
                )
                Spacer(modifier = Modifier.width(padding.dimension8))
                FMClickableOutlinedRow(
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.city),
                    value = uiState.selectedCity,
                    icon = Icons.Default.ArrowDropDown,
                    indicatorsColor = colors.text.copy(alpha = 0.3f),
                    onClick = { onNavigationDistrictsBottomSheet() }
                )
            }
            Spacer(modifier = Modifier.height(padding.dimension8))

            FMOutlineTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = padding.dimension56, end = padding.dimension16),
                value = uiState.openAddress,
                onValueChange = { onAction(AddressContract.UiAction.OnOpenAddressChanged(it)) },
                label = stringResource(R.string.address),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                indicatorsColor = colors.text.copy(alpha = 0.3f)
            )
            Spacer(modifier = Modifier.height(padding.dimension4))
            Text(
                text = stringResource(R.string.address_info),
                style = typography.bodySmallNormal().copy(
                    color = colors.text.copy(alpha = 0.5f)
                ),
                modifier = Modifier.padding(
                    start = padding.dimension56,
                    end = padding.dimension16,
                    bottom = padding.dimension4,
                    top = padding.dimension4
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = padding.dimension16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_apartment),
                    contentDescription = stringResource(R.string.apartment),
                    modifier = Modifier.size(padding.dimension32)
                )
                Spacer(modifier = Modifier.width(padding.dimension8))
                FMOutlineTextField(
                    value = uiState.addressTitle,
                    onValueChange = { onAction(AddressContract.UiAction.OnAddressTitleChanged(it)) },
                    label = stringResource(R.string.address_title),
                    indicatorsColor = colors.text.copy(alpha = 0.3f),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )
            }
            Spacer(modifier = Modifier.height(padding.dimension16))
        }
        Spacer(modifier = Modifier.height(padding.dimension8))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.white),
        ) {
            FMButton(
                text = stringResource(R.string.save),
                onClick = { onAction(AddressContract.UiAction.SaveAddress) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding.dimension16)
                    .align(Alignment.End),
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun AddressScreenPreview() {
    FMTheme {
        AddressScreen(
            uiState = AddressContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            navAction = AddressNavAction({}, {})
        )
    }
}
