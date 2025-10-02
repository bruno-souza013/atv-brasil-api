package com.fatec.brasilapi.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object CepApi {
    // single instance client
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 1)
            exponentialDelay()
        }
        defaultRequest {
            url("https://brasilapi.com.br/api/")
        }
    }

    suspend fun buscarCep(cep: String): CepResult {
        if(!cep.matches(Regex("\\d{8}"))) {
            return CepResult.Error("CEP inválido. Use 8 dígitos.")
        }
        return try {
            val response = client.get("cep/v2/$cep")
            when(response.status) {
                HttpStatusCode.OK -> {
                    val body: AddressResponse = response.body()
                    CepResult.Success(body)
                }
                HttpStatusCode.NotFound -> CepResult.NotFound("CEP não encontrado")
                else -> CepResult.Error("Erro: ${'$'}{response.status.value}")
            }
        } catch (e: Exception) {
            CepResult.Error("Falha de rede: ${'$'}{e.message}")
        }
    }
}
