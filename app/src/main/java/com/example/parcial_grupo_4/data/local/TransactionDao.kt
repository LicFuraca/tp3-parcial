package com.example.parcial_grupo_4.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions ORDER BY dateEpochMillis DESC")
    fun observeAll(): LiveData<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getById(id: String): TransactionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<TransactionEntity>)

    @Query("DELETE FROM transactions")
    suspend fun clear()

    /** Reemplaza todo el historial cacheado por el que vino de la API. */
    @Transaction
    suspend fun replaceAll(items: List<TransactionEntity>) {
        clear()
        insertAll(items)
    }
}
