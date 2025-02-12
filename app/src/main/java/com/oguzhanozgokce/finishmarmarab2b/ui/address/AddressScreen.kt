import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.CollectWithLifecycle
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.showToast
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMButton
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMHorizontalDivider
import com.oguzhanozgokce.finishmarmarab2b.core.presentation.components.FMOutlineTextField
import com.oguzhanozgokce.finishmarmarab2b.ui.address.AddressContract
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.fontSize
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.padding
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun AddressScreen(
    uiState: AddressContract.UiState,
    uiEffect: Flow<AddressContract.UiEffect>,
    onAction: (AddressContract.UiAction) -> Unit,
) {

    val context = LocalContext.current
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            is AddressContract.UiEffect.ShowToast -> context.showToast(effect.message)
        }
    }

    AddressScreenContent(uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreenContent(
    uiState: AddressContract.UiState
) {
    val focusManager = LocalFocusManager.current
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
                title = { Text("Yeni Adres Ekle", style = typography.titleMediumMedium()) },
                navigationIcon = {
                    IconButton(onClick = { /* Geri tuşu işlevi */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Geri",
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colors.white,
                    titleContentColor = colors.black
                )
            )
        }

        Spacer(modifier = Modifier.height(padding.dimension4))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding.dimension4),
            color = colors.error.copy(alpha = 0.1f),
            tonalElevation = 1.dp,
            shape = RoundedCornerShape(padding.dimension8)
        ) {
            Text(
                text = "This address you will add is only valid for purchases in the App. The application will not be displayed in Food or Market.",
                modifier = Modifier.padding(padding.dimension16),
                style = typography.bodySmallNormal().copy(
                    color = colors.error.copy(alpha = 0.7f)
                )
            )
        }
        Spacer(modifier = Modifier.height(padding.dimension4))

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
                text = "Contact Details",
                style = typography.titleMediumMedium().copy(
                    fontSize = fontSize.mediumSmall
                )
            )
            FMHorizontalDivider(modifier = Modifier.padding(horizontal = padding.dimension16))
            Spacer(modifier = Modifier.height(padding.dimension8))
            FMOutlineTextField(
                modifier = Modifier.padding(horizontal = padding.dimension16),
                value = uiState.addressName,
                onValueChange = {},
                label = "Name",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Name"
                    )
                },
                indicatorsColor = colors.text.copy(alpha = 0.3f),
            )
            Spacer(modifier = Modifier.height(padding.dimension8))
            FMOutlineTextField(
                modifier = Modifier.padding(horizontal = padding.dimension16),
                value = uiState.addressSurname,
                onValueChange = {},
                label = "Surname",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Surname"
                    )
                },
                indicatorsColor = colors.text.copy(alpha = 0.3f),
                //focusManager.moveFocus(FocusDirection.Down)
            )
            Spacer(modifier = Modifier.height(padding.dimension8))
            FMOutlineTextField(
                modifier = Modifier.padding(horizontal = padding.dimension16),
                value = uiState.addressTel,
                onValueChange = {},
                label = "Phone",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Phone"
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                indicatorsColor = colors.text.copy(alpha = 0.3f)
            )
            Spacer(modifier = Modifier.height(padding.dimension16))
        }
        Spacer(modifier = Modifier.height(padding.dimension8))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.white),
        ) {
            Text(
                text = "Address Details",
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
                    contentDescription = "Province",
                    modifier = Modifier.size(padding.dimension32)
                )
                Spacer(modifier = Modifier.width(padding.dimension8))
                FMOutlineTextField(
                    modifier = Modifier.weight(1f),
                    value = uiState.province,
                    onValueChange = {},
                    label = "Province",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    indicatorsColor = colors.text.copy(alpha = 0.3f)
                )
                Spacer(modifier = Modifier.width(padding.dimension8))
                FMOutlineTextField(
                    modifier = Modifier.weight(1f),
                    value = uiState.city,
                    onValueChange = {},
                    label = "City",
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    indicatorsColor = colors.text.copy(alpha = 0.3f)
                )
            }

            Spacer(modifier = Modifier.height(padding.dimension8))

            FMOutlineTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 56.dp, end = 16.dp),
                value = uiState.openAddress,
                onValueChange = { },
                label = "Open Address",
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                indicatorsColor = colors.text.copy(alpha = 0.3f)
            )
            Spacer(modifier = Modifier.height(padding.dimension4))
            Text(
                text = "In order for your parcel to reach you safely, you must fill in the neighbourhood, street, street, building and floor information completely.",
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
            FMOutlineTextField(
                modifier = Modifier.padding(start = padding.dimension56, end = padding.dimension16),
                value = uiState.addressTitle,
                onValueChange = {},
                label = "Address Title",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "Zip Code"
                    )
                },
                indicatorsColor = colors.text.copy(alpha = 0.3f),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
        }
        Spacer(modifier = Modifier.height(padding.dimension8))
        FMButton(
            text = "Save",
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding.dimension16)
                .align(Alignment.End),
        )
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
            onAction = {}
        )
    }
} 