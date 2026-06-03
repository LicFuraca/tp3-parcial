package com.example.parcial_grupo_4.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_session")
data class UserEntity(
    @PrimaryKey val id: Int = 1,
    val token: String
)