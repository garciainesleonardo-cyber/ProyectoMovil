package com.example.projectmovil

import androidx.room.Entity
import androidx.room.PrimaryKey

// La anotación @Entity le dice a Room que esta clase representa una tabla.
@Entity(tableName = "fichas_tecnicas")
data class FichaTecnicaEntity(
    // @PrimaryKey define cuál es la columna de clave primaria.
    // autoGenerate = true hace que Room genere un ID único para cada nueva fila.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Aquí defines las otras columnas de tu tabla.
    // Puedes cambiar estos campos según lo que necesites guardar.
    val nombreProducto: String,
    val descripcion: String,
    val numeroSerie: String,
    val fechaCompra: String
)
