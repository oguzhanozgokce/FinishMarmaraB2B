package com.oguzhanozgokce.finishmarmarab2b.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.conditional
import com.oguzhanozgokce.finishmarmarab2b.core.common.extension.noRippleClickable
import com.oguzhanozgokce.finishmarmarab2b.ui.products.ProductListType
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.colors
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme.typography
import kotlinx.coroutines.delay

private const val DELAY_MILLIS_100 = 100L

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FMSearchBar(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "Marka, ürün veya kategori...",
    onValueChange: (String) -> Unit = {},
    onClick: (() -> Unit)? = null,
    onClearClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    focusRequester: FocusRequester = FocusRequester(),
    onSearchClick: (String, ProductListType) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var textFieldValueState by remember {
        mutableStateOf(TextFieldValue(value))
    }

    LaunchedEffect(Unit) {
        withFrameNanos { }
        focusRequester.requestFocus()
        delay(DELAY_MILLIS_100)
        keyboardController?.show()
    }

    BasicTextField(
        value = textFieldValueState,
        onValueChange = {
            textFieldValueState = it
            onValueChange(it.text)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick(textFieldValueState.text, ProductListType.SEARCH)
                keyboardController?.hide()
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .conditional(onClick != null) {
                noRippleClickable { onClick?.invoke() }
            }
            .focusRequester(focusRequester)
            .focusable(),
        singleLine = true,
        textStyle = typography.bodySmallLight().copy(fontWeight = FontWeight.Medium),
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = textFieldValueState.text,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                placeholder = {
                    Text(
                        text = placeholder,
                        style = typography.bodySmallLight(),
                        color = colors.text.copy(alpha = 0.5f)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = colors.onBackground
                    )
                },
                trailingIcon = {
                    if (textFieldValueState.text.isNotEmpty()) {
                        IconButton(onClick = {
                            textFieldValueState = TextFieldValue("")
                            onClearClick?.invoke()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear Icon",
                                tint = colors.text.copy(alpha = 0.7f)
                            )
                        }
                    }
                },
                contentPadding = PaddingValues(horizontal = 8.dp),
                container = {
                    TextFieldDefaults.Container(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = remember { MutableInteractionSource() },
                        focusedIndicatorLineThickness = 0.dp,
                        unfocusedIndicatorLineThickness = 0.dp,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Transparent,
                            focusedContainerColor = colors.cardBackground,
                            unfocusedContainerColor = colors.cardBackground,
                            disabledContainerColor = colors.cardBackground,
                            errorContainerColor = colors.error,
                            disabledLeadingIconColor = colors.text.copy(alpha = 0.5f),
                            errorLeadingIconColor = colors.text,
                        ),
                        shape = RoundedCornerShape(16.dp),
                    )
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
fun QuizAppSearchBarPreview() {
    FMTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FMSearchBar(
                value = "",
                onValueChange = {},
                onClick = {},
                onClearClick = {},
                onSearchClick = { _, _ -> },
                focusRequester = FocusRequester()
            )

            FMSearchBar(
                value = "Laptop",
                onValueChange = {},
                onClick = {},
                onClearClick = {},
                onSearchClick = { _, _ -> },
                focusRequester = FocusRequester()
            )

            FMSearchBar(
                value = "",
                enabled = false,
                onValueChange = {},
                placeholder = "Cannot interact",
                onClick = {},
                onClearClick = {},
                onSearchClick = { _, _ -> },
                focusRequester = FocusRequester()
            )

            FMSearchBar(
                value = "Error example",
                isError = true,
                onValueChange = {},
                placeholder = "Error state",
                onClick = {},
                onClearClick = {},
                onSearchClick = { _, _ -> },
                focusRequester = FocusRequester()
            )
        }
    }
}
