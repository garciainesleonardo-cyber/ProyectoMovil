package com.example.projectmovil.model

import java.io.Serializable

/**
 * Modelo de receta que se pasa entre activities.
 *
 * costLevel: 1 = barato, 2 = medio, 3 = caro
 */
data class Recipe(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val timeMinutes: Int,
    val price: String? = null,      // puede ser null
    val rating: Float = 0f,
    val imageResId: Int,

    // 🔹 NUEVOS CAMPOS PARA FILTROS
    val ingredients: List<String> = emptyList(),
    val costLevel: Int = 2          // por defecto medio
) : Serializable
