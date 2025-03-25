package com.oguzhanozgokce.finishmarmarab2b.ui.editprofile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.EmptyScreen
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMClickableOutlinedRow
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlineTextField
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMPhoneTextField
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMTopBar
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.LoadingBar
import com.oguzhanozgokce.finishmarmarab2b.ui.editprofile.EditProfileContract.UiAction
import com.oguzhanozgokce.finishmarmarab2b.ui.editprofile.EditProfileContract.UiEffect
import com.oguzhanozgokce.finishmarmarab2b.ui.editprofile.EditProfileContract.UiState
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun EditProfileScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    editProfileNavActions: EditProfileNavActions
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> EditProfileContent(
            uiState = uiState,
            onAction = onAction,
            editProfileNavActions = editProfileNavActions,
        )
    }
}

@Composable
fun EditProfileContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    editProfileNavActions: EditProfileNavActions
) {
    Scaffold(
        topBar = {
            FMTopBar(
                title = "Edit Profile",
                onNavigationClick = { editProfileNavActions.navigateToBack() },
                containerColor = FMTheme.colors.cardBackground,
                actions = null
            )
        },
        bottomBar = {
            FMButton(
                text = "Save Changes",
                onClick = { onAction(UiAction.SaveClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp)
            )
        },
        containerColor = FMTheme.colors.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FMOutlineTextField(
                    value = uiState.name,
                    onValueChange = { onAction(UiAction.OnChangeName(it)) },
                    label = "Name",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default,
                    visualTransformation = VisualTransformation.None,
                )
                FMOutlineTextField(
                    value = uiState.surname,
                    onValueChange = { onAction(UiAction.OnChangeSurname(it)) },
                    label = "Surname",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default,
                    visualTransformation = VisualTransformation.None
                )
                FMOutlineTextField(
                    value = uiState.email,
                    onValueChange = { onAction(UiAction.OnChangeEmail(it)) },
                    label = "Email",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    enabled = false,
                    visualTransformation = VisualTransformation.None
                )
                FMPhoneTextField(
                    modifier = Modifier.fillMaxWidth(),
                    phoneNumber = uiState.phoneNumber,
                    onPhoneNumberChange = { onAction(UiAction.OnChangePhoneNumber(it)) },
                    isError = false,
                    errorMessage = null
                )
                BirthDatePickerField(
                    birthDate = uiState.birthDate,
                    onDateSelected = { onAction(UiAction.OnChangeBirthDate(it)) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDatePickerField(
    birthDate: String,
    onDateSelected: (String) -> Unit
) {
    var isDatePickerVisible by remember { mutableStateOf(false) }
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    FMClickableOutlinedRow(
        value = birthDate,
        label = "Birth Date",
        modifier = Modifier.fillMaxWidth(),
        onClick = { isDatePickerVisible = true }
    )

    if (isDatePickerVisible) {
        val initialMillis = try {
            LocalDate.parse(birthDate, dateFormatter)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()
        } catch (e: Exception) {
            System.currentTimeMillis()
        }

        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = initialMillis
        )
        DatePickerDialog(
            onDismissRequest = { isDatePickerVisible = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedMillis = datePickerState.selectedDateMillis
                        if (selectedMillis != null) {
                            val selectedDate = Instant.ofEpochMilli(selectedMillis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            onDateSelected(selectedDate.format(dateFormatter))
                        }
                        isDatePickerVisible = false
                    }
                ) {
                    Text(
                        "OK", style = FMTheme.typography.bodySmallNormal().copy(
                            fontSize = FMTheme.fontSize.mediumSmall
                        )
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { isDatePickerVisible = false }) {
                    Text(
                        "Cancel", style = FMTheme.typography.bodySmallNormal().copy(
                            fontSize = FMTheme.fontSize.mediumSmall
                        )
                    )
                }
            },
            colors = DatePickerDefaults.colors(
                containerColor = FMTheme.colors.background,
            ),
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = FMTheme.colors.background,
                    titleContentColor = FMTheme.colors.text,
                    dayContentColor = FMTheme.colors.text,
                    yearContentColor = FMTheme.colors.text,
                    selectedDayContentColor = FMTheme.colors.text,
                    selectedDayContainerColor = FMTheme.colors.primary,
                    disabledDayContentColor = FMTheme.colors.text,
                    selectedYearContainerColor = FMTheme.colors.primary,
                    selectedYearContentColor = FMTheme.colors.text,
                    disabledYearContentColor = FMTheme.colors.text,
                    disabledSelectedYearContentColor = FMTheme.colors.text,
                    todayDateBorderColor = FMTheme.colors.primary,
                    todayContentColor = FMTheme.colors.text
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
fun EditProfileScreenPreview(
) {
    FMTheme {
        EditProfileScreen(
            uiState = UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            editProfileNavActions = EditProfileNavActions(
                navigateToBack = { },
            )
        )
    }
}
