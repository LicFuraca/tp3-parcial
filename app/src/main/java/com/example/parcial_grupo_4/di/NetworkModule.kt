package com.example.parcial_grupo_4.di

import com.example.parcial_grupo_4.data.api.ShopService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://6d710e79-f4ca-4651-909f-7dd13bd29968.mock.pstmn.io/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            // No necesitamos KotlinJsonAdapterFactory si usamos ksp(libs.moshi.kotlin.codegen)
            // y @JsonClass(generateAdapter = true) en todas nuestras clases de datos.
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideShopService(retrofit: Retrofit): ShopService {
        return retrofit.create(ShopService::class.java)
    }
}
