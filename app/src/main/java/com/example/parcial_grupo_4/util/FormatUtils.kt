package com.example.parcial_grupo_4.util

import java.text.NumberFormat
import java.util.Locale

object FormatUtils {

    fun formatCurrencyAmount(amount: Double): String {
        val formatter = NumberFormat.getNumberInstance(Locale("es", "AR")).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }

        return formatter.format(amount)
    }
}