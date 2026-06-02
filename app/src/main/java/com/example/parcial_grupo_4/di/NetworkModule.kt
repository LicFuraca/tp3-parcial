package com.example.parcial_grupo_4.di

import com.example.parcial_grupo_4.BuildConfig
import com.example.parcial_grupo_4.data.api.ApiConstants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.example.parcial_grupo_4.data.api.AuthApi

/**
 * Provee la infraestructura de red compartida (OkHttp + Moshi + Retrofit) como
 * singletons. Para consumir un endpoint, un dev sólo necesita:
 *
 * 1. Definir su `interface XApi` con métodos `suspend` anotados (`@GET`, `@POST`, ...).
 * 2. Proveer el service: `@Provides fun provideXApi(retrofit: Retrofit) = retrofit.create(XApi::class.java)`.
 * 3. Inyectar el service en el repositorio y envolver las llamadas con `safeApiCall { ... }`.
 *
 * Los DTOs deben anotarse con `@JsonClass(generateAdapter = true)` (Moshi codegen).
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 30L

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            // Sólo en debug logueamos el cuerpo: en release evitamos volcar
            // tokens o datos sensibles a logcat.
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}
