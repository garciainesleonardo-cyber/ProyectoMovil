package com.example.projectmovil.data

/**
 * Modelo de datos para una Receta Histórica.
 * Todos los campos deben coincidir con la información que se usará en la UI y los filtros.
 */
data class HistoricalRecipe(
    val name: String,
    val shortDescription: String,
    val type: String, // Crucial para el filtro de Tipo de Plato
    val rating: String, // Ejemplo: "⭐ 4.6"
    val calories: String, // Ejemplo: "280 kcal por porción"
    val history: String,
    val ingredients: List<String>,
    val preparation: List<String>
)

// ====================================================================
// FUNCIONES DE EXTENSIÓN PARA EL FILTRADO
// ====================================================================

/**
 * Extrae el valor numérico del campo 'rating'.
 * Convierte "⭐ 4.6" a Double (4.6).
 */
fun HistoricalRecipe.getNumericRating(): Double {
    return this.rating
        .replace("⭐ ", "")
        .toDoubleOrNull() ?: 0.0 // Devuelve 0.0 si falla
}

/**
 * Extrae el valor numérico del campo 'calories'.
 * Convierte "280 kcal por porción" a Int (280).
 */
fun HistoricalRecipe.getNumericCalories(): Int {
    // Busca uno o más dígitos (\d+) seguidos de " kcal"
    val regex = "(\\d+) kcal".toRegex()
    val match = regex.find(this.calories)

    // Si encuentra la coincidencia, toma el primer grupo capturado (los dígitos).
    // Devuelve Int.MAX_VALUE si falla (por si hay un registro mal formado),
    // lo cual lo pondrá al final en un orden ascendente.
    return match?.groupValues?.get(1)?.toIntOrNull() ?: Int.MAX_VALUE
}