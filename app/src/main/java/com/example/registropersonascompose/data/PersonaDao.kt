package com.example.registropersonascompose.data

import androidx.room.*
import com.example.registropersonascompose.model.Persona
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(persona: Persona)

    @Delete
    suspend fun eliminar(persona: Persona)

    @Query("SELECT * FROM persona WHERE personaId =:personaId")

    fun buscar(personaId: Int): Flow<Persona>

    @Query("SELECT * FROM persona ORDER BY personaId")
    fun getList(): Flow<List<Persona>>

}