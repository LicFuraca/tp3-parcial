package com.example.parcial_grupo_4.di

import android.content.Context
import androidx.room.Room
import com.example.parcial_grupo_4.data.local.AuthDao
import com.example.parcial_grupo_4.data.local.LendlyDatabase
import com.example.parcial_grupo_4.data.local.TransactionDao // Asegúrate de tener este import
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LendlyDatabase {
        return Room.databaseBuilder(
            context,
            LendlyDatabase::class.java,
            "lendly_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideAuthDao(database: LendlyDatabase): AuthDao {
        return database.authDao()
    }

    @Provides
    fun provideTransactionDao(database: LendlyDatabase): TransactionDao {
        return database.transactionDao()
    }
}
