package com.example.registropersonascompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Persona")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val personaId: Int = 0,
    val nombres: String,
    val email: String,
    val ocupacionId: Int,
    val salario: Float
)
