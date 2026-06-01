package com.example.parcial_grupo_4.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.parcial_grupo_4.data.api.NetworkResult
import com.example.parcial_grupo_4.data.api.TransactionApi
import com.example.parcial_grupo_4.data.api.safeApiCall
import com.example.parcial_grupo_4.data.local.TransactionDao
import com.example.parcial_grupo_4.data.model.toEntity
import com.example.parcial_grupo_4.data.model.toUiModel
import com.example.parcial_grupo_4.ui.history.TransactionUiModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Orquesta API + Room con estrategia offline-first: la UI observa Room (fuente de
 * verdad) y [refresh] trae de la red para actualizar el cache.
 */
@Singleton
class TransactionRepository @Inject constructor(
    private val api: TransactionApi,
    private val dao: TransactionDao,
) {
    fun observeTransactions(): LiveData<List<TransactionUiModel>> =
        dao.observeAll().map { entities -> entities.map { it.toUiModel() } }

    suspend fun refresh(): NetworkResult<Unit> =
        when (val result = safeApiCall { api.getTransactions() }) {
            is NetworkResult.Success -> {
                dao.replaceAll(result.data.transactions.map { it.toEntity() })
                NetworkResult.Success(Unit)
            }
            is NetworkResult.Error -> result
        }

    suspend fun getById(id: String): TransactionUiModel? = dao.getById(id)?.toUiModel()
}
