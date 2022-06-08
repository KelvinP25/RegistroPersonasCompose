package com.example.registropersonascompose.data.Repository

import com.example.registropersonascompose.data.OcupacionDao
import com.example.registropersonascompose.model.Ocupacion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OcupacionRepository @Inject constructor(
    val ocupacionDao: OcupacionDao
) {
    suspend fun insertar(ocupacion: Ocupacion){
        ocupacionDao.insertar(ocupacion)
    }

    suspend fun eliminar(ocupacion: Ocupacion){
        ocupacionDao.eliminar(ocupacion)
    }

    fun buscar(ocupacionId: Int): Flow<Ocupacion> {
        return ocupacionDao.buscar(ocupacionId)
    }
    fun getList(): Flow<List<Ocupacion>> {
        return ocupacionDao.getList()
    }
}