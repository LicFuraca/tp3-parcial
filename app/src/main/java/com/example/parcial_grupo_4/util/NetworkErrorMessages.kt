package com.example.parcial_grupo_4.util

import androidx.annotation.StringRes
import com.example.parcial_grupo_4.R
import com.example.parcial_grupo_4.data.api.NetworkErrorType

/** Mapea un error de red a un texto de UI (en `strings.xml`) para mostrar al usuario. */
@StringRes
fun NetworkErrorType.toMessageRes(): Int = when (this) {
    NetworkErrorType.NoConnection -> R.string.error_no_connection
    NetworkErrorType.Unauthorized -> R.string.error_unauthorized
    NetworkErrorType.NotFound -> R.string.error_not_found
    NetworkErrorType.Server -> R.string.error_server
    NetworkErrorType.Unknown -> R.string.error_unknown
}
