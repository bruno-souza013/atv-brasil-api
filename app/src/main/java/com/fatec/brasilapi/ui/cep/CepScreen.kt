package com.fatec.brasilapi.ui.cep

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CepScreen(vm: CepViewModel = viewModel()) {
    val state by vm.state.collectAsState()
    val focus = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(48.dp))
        OutlinedTextField(
            value = state.input,
            onValueChange = vm::onInputChange,
            label = { Text("CEP (8 dígitos)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                focus.clearFocus(); vm.buscar()
            }),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { vm.buscar() }, enabled = state.input.length == 8 && !state.isLoading) {
                Text("Buscar")
            }
        }
        Spacer(Modifier.height(16.dp))

        when {
            state.isLoading -> CircularProgressIndicator()
            state.errorMessage != null -> Text(state.errorMessage ?: "Erro", color = MaterialTheme.colorScheme.error)
            state.address != null -> {
                AlertDialog(
                    onDismissRequest = { vm.dismiss() },
                    confirmButton = {
                        TextButton(onClick = { vm.dismiss() }) {
                            Text("Fechar")
                        }
                    },
                    title = { Text("Endereço") },
                    text = {
                        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text("CEP: ${state.address?.cep}")
                            Text("Estado: ${state.address?.state}")
                            Text("Cidade: ${state.address?.city}")
                            Text("Bairro: ${state.address?.neighborhood ?: "-"}")
                            Text("Rua: ${state.address?.street ?: "-"}")
                        }
                    }
                )
            }
            else -> Text("Informe um CEP para consulta")
        }
    }
}

@Composable
private fun AddressCard(state: CepScreenState) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("CEP: ${state.address?.cep}")
            Text("Estado: ${state.address?.state}")
            Text("Cidade: ${state.address?.city}")
            Text("Bairro: ${state.address?.neighborhood ?: "-"}")
            Text("Rua: ${state.address?.street ?: "-"}")
        }
    }
}
