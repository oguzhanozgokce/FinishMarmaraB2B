package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.oguzhanozgokce.finishmarmarab2b.ecommerce.domain.model.City
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData
import com.oguzhanozgokce.finishmarmarab2b.ui.mock.PreviewMockData.sampleCities
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FMModelBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
    dragHandle: (@Composable () -> Unit)? = { BottomSheetDefaults.DragHandle() },
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(topStart = padding.dimension16, topEnd = padding.dimension16),
        containerColor = colors.white,
        contentColor = colors.white,
        tonalElevation = padding.dimension4,
        scrimColor = colors.black.copy(alpha = 0.5f),
        dragHandle = { dragHandle?.invoke() },
        content = { content() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FMCitiesBottomSheetContent(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    cities: List<City>,
    onCitySelected: (City) -> Unit
) {
    var selectedCity by remember { mutableStateOf<City?>(null) }

    FMModelBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.white)
                    .padding(
                        start = padding.dimension8,
                        end = padding.dimension8,
                        bottom = padding.dimension8
                    )
            ) {
                items(cities) { city ->
                    CityItem(
                        name = city.cityName,
                        isSelected = city == selectedCity,
                        textColor = if (city == selectedCity) colors.primary else colors.text,
                        onClick = {
                            selectedCity = city
                            onCitySelected(city)
                        }
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FMDistrictsBottomSheetContent(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    selectedCity: City,
    selectedDistrict: String?,
    onDistrictSelected: (String) -> Unit
) {

    FMModelBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.white)
                    .padding(
                        start = padding.dimension8,
                        end = padding.dimension8,
                        bottom = padding.dimension8
                    )
            ) {
                items(selectedCity.districts) { district ->
                    CityItem(
                        name = district,
                        isSelected = district == selectedDistrict,
                        textColor = if (district == selectedDistrict) colors.primary else colors.text,
                        onClick = { onDistrictSelected(district) }
                    )
                }
            }
        }
    )
}

@Composable
fun CityItem(
    name: String,
    isSelected: Boolean = false,
    textColor: Color = colors.text,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.white)
            .padding(padding.dimension8)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            style = typography.bodyMediumNormal().copy(
                fontSize = fontSize.mediumSmall,
                color = textColor
            ),
        )

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = colors.primary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewFMCitiesBottomSheetContent() {

    val fakeSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    FMTheme {
        FMCitiesBottomSheetContent(
            sheetState = fakeSheetState,
            onDismissRequest = {},
            cities = sampleCities,
            onCitySelected = { selectedCity ->
                println("Selected City: ${selectedCity.cityName}")
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewFMDistrictsBottomSheetContent() {

    val fakeSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    FMTheme {
        FMDistrictsBottomSheetContent(
            sheetState = fakeSheetState,
            onDismissRequest = {},
            selectedCity = PreviewMockData.sampleCity,
            selectedDistrict = null,
            onDistrictSelected = { selectedDistrict ->
                println("Selected District: $selectedDistrict")
            }
        )
    }
}
