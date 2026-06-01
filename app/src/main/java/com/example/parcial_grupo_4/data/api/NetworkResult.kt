package com.example.parcial_grupo_4.data.api

/**
 * Resultado tipado de una llamada de red. Pensado para que los repositorios
 * devuelvan esto y los ViewModels lo expongan vía LiveData sin tener que manejar
 * try/catch ni exponer stack traces a la UI.
 */
sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(
        val type: NetworkErrorType,
        val httpCode: Int? = null,
    ) : NetworkResult<Nothing>
}

/** Categoría de error de red, agnóstica de UI. La capa de presentación la mapea a un texto. */
enum class NetworkErrorType {
    NoConnection,
    Unauthorized,
    NotFound,
    Server,
    Unknown,
}
