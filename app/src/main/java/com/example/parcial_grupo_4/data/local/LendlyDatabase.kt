package com.example.parcial_grupo_4.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class LendlyDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun authDao(): AuthDao
}
