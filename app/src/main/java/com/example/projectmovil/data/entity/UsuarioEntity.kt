package com.example.projectmovil.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de Room que representa la tabla 'usuarios' en la base de datos.
 * Contiene todos los datos de autenticación, perfil y biometría del usuario.
 */
@Entity(tableName = "usuarios")
data class UsuarioEntity(
    // ----------------------------------------------------
    //  CAMPOS DE AUTENTICACIÓN
    // ----------------------------------------------------

    // Clave primaria, se autoincrementa con cada nuevo usuario.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Email usado para el inicio de sesión
    val email: String,

    // Contraseña (IMPORTANTE: En un entorno de producción, DEBE estar hasheada)
    val contrasena: String,

    // ----------------------------------------------------
    //  CAMPOS DE PERFIL BÁSICO
    // ----------------------------------------------------
    val nombre: String,
    val apellido: String,
    val objetivo: String, // Ej: "Perder peso", "Mantener"
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
    val imc: Double, // Índice de Masa Corporal
    val nivelActividad: String, // Ej: "Sedentario", "Activo"

    // ----------------------------------------------------
    //  DATOS DE SALUD Y DIETA (Guardados como texto/CSV)
    // ----------------------------------------------------
    // Se almacenan como una sola cadena de texto, idealmente separada por comas (CSV)
    val condicionesMedicas: String,
    val preferenciasDieteticas: String,
    val alergias: String,
    val comidasNoDeseadas: String
)