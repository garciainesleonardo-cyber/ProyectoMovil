package com.example.projectmovil.model

import java.io.Serializable

data class Recipe(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val timeMinutes: Int,
    val price: String,
    val rating: Float,
    val imageResId: Int
) : Serializable
