package com.example.parcial_grupo_4.di

import android.content.Context
import androidx.room.Room
import com.example.parcial_grupo_4.data.local.LendlyDatabase
import com.example.parcial_grupo_4.data.local.TransactionDao
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
    fun provideDatabase(@ApplicationContext context: Context): LendlyDatabase =
        Room.databaseBuilder(context, LendlyDatabase::class.java, "lendly.db").build()

    @Provides
    fun provideTransactionDao(database: LendlyDatabase): TransactionDao =
        database.transactionDao()
}
