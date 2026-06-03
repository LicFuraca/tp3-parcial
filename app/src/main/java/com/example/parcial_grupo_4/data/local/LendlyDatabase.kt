package com.example.parcial_grupo_4.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class], version = 2, exportSchema = false)
abstract class LendlyDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
