package com.fatec.brasilapi.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressResponse(
    val cep: String? = null,
    val state: String? = null,
    val city: String? = null,
    @SerialName("neighborhood") val neighborhood: String? = null,
    val street: String? = null,
)

sealed interface CepResult {
    data class Success(val data: AddressResponse): CepResult
    data class NotFound(val message: String): CepResult
    data class Error(val message: String): CepResult
}
