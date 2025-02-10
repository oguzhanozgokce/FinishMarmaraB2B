import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.oguzhanozgokce.finishmarmarab2b.ui.theme.FMTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var neighborhood by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var addressTitle by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).verticalScroll(rememberScrollState())
    ) {
        // Üst Bar
        TopAppBar(
            title = { Text("Yeni Adres Ekle") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("payment_screen") {
                        popUpTo("payment_screen") { inclusive = true }
                    }
                }) {
                    Icon(Icons.Default.ArrowBack, "Geri")
                }
            }
        )
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {


            // Uyarı Mesajı
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceVariant,
                tonalElevation = 1.dp
            ) {
                Text(
                    text = "Ekleyeceğiniz bu adres sadece Uygulamadaki alışverişler için geçerlidir. Uygulama Yemek veya Market içerisinde gösterilmeyecektir.",
                    modifier = Modifier.padding(FMTheme.padding.dimension16)
                )
            }

            Spacer(modifier = Modifier.height(FMTheme.padding.dimension16))

            // İletişim Bilgileri Bölümü
            Text(
                text = "İletişim Bilgileri",
                style = FMTheme.typography.titleMediumMedium()
            )

            Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Ad") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
                )
            )

            Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))

            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text("Soyad") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                })
            )

            Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Cep Telefonu") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(FMTheme.padding.dimension16))

            // Adres Bilgileri Bölümü
            Text(
                text = "Adres Bilgileri",
                style = FMTheme.typography.titleMediumMedium()
            )

            Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(FMTheme.padding.dimension8)
            ) {
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text("İl") },
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = district,
                    onValueChange = { district = it },
                    label = { Text("İlçe") },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))

            OutlinedTextField(
                value = neighborhood,
                onValueChange = { neighborhood = it },
                label = { Text("Mahalle") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(FMTheme.padding.dimension8))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Adres") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Text(
                text = "Paketinin sana güvenle ulaşması için mahalle, cadde, sokak, bina ve kat bilgilerini eksiksiz doldurmalısın.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = FMTheme.padding.dimension8)
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
        // Preview için fake NavController oluşturuyoruz
        val previewNavController = rememberNavController()
        AddressScreen(previewNavController)
    }
} 