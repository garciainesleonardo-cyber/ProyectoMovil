package com.example.projectmovil.data // Asegúrate que el paquete sea 'data'

// 1. EL MODELO DE DATOS
data class HistoricalRecipe(
    val name: String,
    val shortDescription: String,
    val type: String,               // <--- Crucial para el filtro de Tipo
    val rating: String,
    val calories: String,
    val history: String,
    val ingredients: List<String>,
    val preparation: List<String>
)

// 2. FUNCIONES DE EXTENSIÓN PARA EL FILTRADO

// Función para obtener el rating numérico (Ej: de "⭐ 4.6" a 4.6)
fun HistoricalRecipe.getNumericRating(): Double {
    return this.rating
        .replace("⭐ ", "")
        .toDoubleOrNull() ?: 0.0
}

// Función para obtener las calorías numéricas (Ej: de "280 kcal por porción" a 280)
fun HistoricalRecipe.getNumericCalories(): Int {
    val regex = "(\\d+) kcal".toRegex()
    val match = regex.find(this.calories)
    return match?.groupValues?.get(1)?.toIntOrNull() ?: Int.MAX_VALUE
}