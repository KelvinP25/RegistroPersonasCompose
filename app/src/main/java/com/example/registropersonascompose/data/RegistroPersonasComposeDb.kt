package com.example.registropersonascompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registropersonascompose.model.Ocupacion
import com.example.registropersonascompose.model.Persona

@Database(
    entities = [Ocupacion::class, Persona::class],
    exportSchema = false,
    version = 1
)

abstract class RegistroPersonasComposeDb : RoomDatabase() {

    abstract val ocupacionDao: OcupacionDao
    abstract val personaDao: PersonaDao
}