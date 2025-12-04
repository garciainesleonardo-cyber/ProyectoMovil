package com.example.projectmovil.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    // ----------------------------------------------------
    //  CAMPOS DE AUTENTICACIÓN
    // ----------------------------------------------------
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val contrasena: String, // Contraseña SIN hashear (¡Recuerda que en producción debe ser hasheada!)

    // ----------------------------------------------------
    //  CAMPOS DE PERFIL BÁSICO
    // ----------------------------------------------------
    val nombre: String,
    val apellido: String,
    val objetivo: String, // Ej: "Perder peso"
    val genero: String,
    val edad: Int,

    // ----------------------------------------------------
    //  DATOS BIOMÉTRICOS
    // ----------------------------------------------------
    val altura: Double,
    val unidadAltura: String, // "cm" o "ft"
    val pesoActual: Double,
    val unidadPeso: String, // "kg" o "lb"
    val pesoObjetivo: Double,
    val imc: Double,
    val nivelActividad: String,

    // ----------------------------------------------------
    //  DATOS DE SALUD Y DIETA (Guardados como CSV)
    // ----------------------------------------------------
    val condicionesMedicas: String, // CSV de condiciones
    val preferenciasDieteticas: String, // CSV de preferencias
    val alergias: String, // CSV de alergias
    val comidasNoDeseadas: String
)