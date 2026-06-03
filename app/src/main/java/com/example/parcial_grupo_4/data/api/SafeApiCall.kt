package com.example.parcial_grupo_4.data.api

import retrofit2.HttpException
import java.io.IOException

/**
 * Envuelve una llamada Retrofit (`suspend`) y traduce las excepciones a [NetworkResult].
 * Uso típico en un repositorio:
 *
 * ```
 * suspend fun getTransactions(): NetworkResult<List<TransactionDto>> =
 *     safeApiCall { api.getTransactions() }
 * ```
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> = try {
    NetworkResult.Success(apiCall())
} catch (e: IOException) {
    // Sin conexión, timeout o host inalcanzable.
    NetworkResult.Error(NetworkErrorType.NoConnection)
} catch (e: HttpException) {
    NetworkResult.Error(httpErrorType(e.code()), e.code())
} catch (e: Exception) {
    NetworkResult.Error(NetworkErrorType.Unknown)
}

private fun httpErrorType(code: Int): NetworkErrorType = when (code) {
    401, 403 -> NetworkErrorType.Unauthorized
    404 -> NetworkErrorType.NotFound
    in 500..599 -> NetworkErrorType.Server
    else -> NetworkErrorType.Unknown
}
