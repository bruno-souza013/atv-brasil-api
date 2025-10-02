package com.fatec.brasilapi.ui.cep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatec.brasilapi.data.AddressResponse
import com.fatec.brasilapi.data.CepApi
import com.fatec.brasilapi.data.CepResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CepScreenState(
    val input: String = "",
    val address: AddressResponse? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class CepViewModel: ViewModel() {
    private val _state = MutableStateFlow(CepScreenState())
    val state: StateFlow<CepScreenState> = _state.asStateFlow()

    fun onInputChange(newValue: String) {
        _state.value = _state.value.copy(input = newValue.filter { it.isDigit() }.take(8))
    }

    fun buscar() {
        val cep = _state.value.input
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMessage = null, address = null)
            when(val result = CepApi.buscarCep(cep)) {
                is CepResult.Success -> _state.value = _state.value.copy(isLoading = false, address = result.data)
                is CepResult.NotFound -> _state.value = _state.value.copy(isLoading = false, errorMessage = result.message)
                is CepResult.Error -> _state.value = _state.value.copy(isLoading = false, errorMessage = result.message)
            }
        }
    }

    fun limpar() {
        _state.value = CepScreenState()
    }
}
