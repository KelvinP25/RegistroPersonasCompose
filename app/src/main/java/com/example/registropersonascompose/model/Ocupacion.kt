package com.example.registropersonascompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ocupacion")
data class Ocupacion(
    @PrimaryKey(autoGenerate = true)
    val ocupacionId: Int = 0,
    val nombres: String
)

