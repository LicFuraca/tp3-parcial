package com.example.parcial_grupo_4.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: String,
    val type: String,
    val title: String,
    val description: String,
    val amount: Double,
    val currency: String,
    val status: String,
    val date: String,
    // Epoch en millis derivado de [date], para ordenar y agrupar sin re-parsear.
    val dateEpochMillis: Long,
    val loanId: String?,
    val referenceNumber: String,
)
