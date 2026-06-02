package com.example.parcial_grupo_4.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AuthDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToken(user: UserEntity)

    @Query("SELECT token FROM user_session WHERE id = 1")
    suspend fun getToken(): String?
}

