package com.example.parcial_grupo_4.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowUpward
import com.example.parcial_grupo_4.data.api.dto.TransactionDto
import com.example.parcial_grupo_4.data.local.TransactionEntity
import com.example.parcial_grupo_4.ui.history.TransactionUiModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.abs

fun TransactionDto.toEntity(): TransactionEntity = TransactionEntity(
    id = id,
    type = type,
    title = title,
    description = description,
    amount = amount,
    currency = currency,
    status = status,
    date = date,
    dateEpochMillis = parseIsoToMillis(date),
    loanId = loanId,
    referenceNumber = referenceNumber,
)

fun TransactionEntity.toUiModel(): TransactionUiModel {
    val (head, company) = splitTitle(title)
    return TransactionUiModel(
        id = id,
        title = head,
        subtitleCompany = company.ifBlank { description },
        time = formatTime(dateEpochMillis),
        amount = formatAmount(amount, currency),
        icon = if (amount < 0) Icons.Outlined.ArrowUpward else Icons.Outlined.Add,
        description = description,
        dateTime = formatDateTime(dateEpochMillis),
        referenceNumber = referenceNumber,
        status = status.toDisplayCase(),
        monthLabel = formatMonth(dateEpochMillis),
        type = type,
    )
}

private const val ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

private fun parseIsoToMillis(iso: String): Long = try {
    SimpleDateFormat(ISO_PATTERN, Locale.US)
        .apply { timeZone = TimeZone.getTimeZone("UTC") }
        .parse(iso)?.time ?: 0L
} catch (e: Exception) {
    0L
}

private fun formatTime(millis: Long): String =
    SimpleDateFormat("h:mm a", Locale.US).format(Date(millis))

private fun formatDateTime(millis: Long): String =
    SimpleDateFormat("MMM d, yyyy · h:mm a", Locale.US).format(Date(millis))

private fun formatMonth(millis: Long): String =
    SimpleDateFormat("MMMM yyyy", Locale.US).format(Date(millis))

private fun formatAmount(amount: Double, currency: String): String {
    val symbol = if (currency.equals("PHP", ignoreCase = true)) "₱" else "$currency "
    val sign = if (amount < 0) "-" else "+"
    val formatted = NumberFormat.getNumberInstance(Locale.US).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }.format(abs(amount))
    return "$sign$symbol$formatted"
}

/** La API embebe la contraparte en el título: "Loan Payment — Nike Inc." → ("Loan Payment", "Nike Inc."). */
private fun splitTitle(title: String): Pair<String, String> {
    val parts = title.split("—", limit = 2)
    return if (parts.size == 2) parts[0].trim() to parts[1].trim() else title.trim() to ""
}

/** "LOAN_PAYMENT" → "Loan Payment". */
private fun String.toDisplayCase(): String =
    split("_").joinToString(" ") { word -> word.lowercase().replaceFirstChar { it.uppercase() } }
