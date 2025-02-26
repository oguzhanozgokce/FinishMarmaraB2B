package com.oguzhanozgokce.finishmarmarab2b.ui.payment.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

@Composable
fun FMNewCreditCart(
) {
    var cardNumber by remember { mutableStateOf("") }
    var selectedMonth by remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(FMTheme.colors.background)
    ) {
        Text(
            text = "Card Number",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        OutlinedTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Card Holder",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        OutlinedTextField(
            value = cardName,
            onValueChange = { cardName = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = " Expiration date",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Row {

                    OutlinedTextField(
                        value = selectedMonth,
                        onValueChange = { selectedMonth = it },
                        placeholder = { Text("Month") },
                        modifier = Modifier.weight(2.5f),
                        trailingIcon = {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Ay",
                                    tint = FMTheme.colors.lightGray
                                )
                            }


                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = selectedYear,
                        onValueChange = { selectedYear = it },
                        placeholder = { Text("Year") },
                        modifier = Modifier.weight(2.5f),
                        trailingIcon = {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Year",
                                    tint = FMTheme.colors.lightGray
                                )
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(0.4f)
            ) {
                Text(
                    text = "CVV",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = cvv,
                        onValueChange = { cvv = it },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "CVV",
                        tint = FMTheme.colors.lightGray
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun FMNewCreditCartPreview() {
    FMTheme {
        FMNewCreditCart(
        )

    }
}