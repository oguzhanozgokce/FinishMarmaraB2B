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
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

@Composable
fun FMNewCreditCart(
    creditCard: CreditCard,
    onCreditCard: (CreditCard) -> Unit,
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
        // Üst kısım: Kart Numarası
        Text(
            text = "Kart Numarası",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        OutlinedTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            placeholder = { Text("XXXX XXXX XXXX XXXX") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Kart Sahibi",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        OutlinedTextField(
            value = cardName,
            onValueChange = { cardName = it },
            placeholder = { Text("İsim Soyisim") },
            modifier = Modifier.fillMaxWidth()
        )

        // Alt kısım: Son Kullanma Tarihi ve CVV
        Row(modifier = Modifier.fillMaxWidth()) {

            // Solda: Son Kullanma Tarihi
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = "Son Kullanma Tarihi",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    // Ay
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
                    // Yıl
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

            // Sağda: CVV
            Column(
                modifier = Modifier
                    .weight(0.4f)
            ) {
                Text(
                    text = "CVV",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    value = cvv,
                    onValueChange = { cvv = it },
                    placeholder = { Text("***") },
                    singleLine = true,
                    // CVV alanı için trailingIcon ekleyebilirsiniz
                    trailingIcon = {
                        IconButton(onClick = {
                            // Burada "?" ikonuna tıklanınca yapılacak işlemi ekleyebilirsiniz
                        }) {
                            Icon(
                                imageVector = Icons.Default.Info, // veya kendi ? ikonunuz
                                contentDescription = "CVV Info",
                                tint = Color.Gray
                            )
                        }
                    }
                )
            }
        }
    }


}

@Preview
@Composable
fun FMNewCreditCartPreview() {
    FMTheme {
        FMNewCreditCart(
            creditCard = CreditCard(
                cardTitle = "Test",
                cardNumber = "5367890123456436",
                cardHolder = "John Doe",
                expirationDate = "12/25",
                cvv = "123",
                cardType = CardType.MASTERCARD,
            ),
            onCreditCard = {}
        )
    }
}