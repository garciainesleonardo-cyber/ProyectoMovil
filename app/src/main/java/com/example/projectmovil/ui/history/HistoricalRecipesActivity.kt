package com.example.projectmovil.ui.history

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmovil.R
import com.example.projectmovil.data.HistoricalRecipe
import com.example.projectmovil.data.HistoricalRecipeProvider
// Importar las funciones de extensión que están en HistoricalRecipe.kt
import com.example.projectmovil.data.getNumericCalories
import com.example.projectmovil.data.getNumericRating
// Importar el Chip de Material Design
import com.google.android.material.chip.Chip

class HistoricalRecipesActivity : AppCompatActivity() {

    // 1. Listas y Referencias
    private val allRecipes: List<HistoricalRecipe> by lazy {
        HistoricalRecipeProvider.historicalRecipes
    }
    private lateinit var recipesContainer: LinearLayout

    // 2. Variables de Estado de los Filtros
    private var currentTypeFilter: String? = null
    private var currentMinRating: Double? = null
    private var currentMaxCalories: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical_recipes)

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        recipesContainer = findViewById(R.id.recipes_container)

        // Regresar Activity
        btnBack.setOnClickListener { finish() }

        // Inicializar filtros y aplicar el renderizado inicial
        setupFilterChips()
        applyFilters()
    }

    // =========================================================================
    // I. SETUP DE CHIPS Y DIÁLOGOS
    // =========================================================================

    private fun setupFilterChips() {
        // Asigna el OnClickListener a los Chips del XML
        findViewById<Chip>(R.id.filter_type).setOnClickListener {
            showTypeFilterDialog()
        }
        findViewById<Chip>(R.id.filter_rating).setOnClickListener {
            showRatingFilterDialog()
        }
        findViewById<Chip>(R.id.filter_calories).setOnClickListener {
            showCaloriesFilterDialog()
        }
    }

    private fun showTypeFilterDialog() {
        val uniqueTypes = allRecipes.map { it.type }.distinct().toMutableList()
        uniqueTypes.add(0, "Todos")

        AlertDialog.Builder(this)
            .setTitle("Filtrar por Tipo")
            .setItems(uniqueTypes.toTypedArray()) { _, which ->
                val selectedType = uniqueTypes[which]

                // 1. Actualiza el estado del filtro
                currentTypeFilter = if (selectedType == "Todos") null else selectedType

                // 2. Actualiza el texto del chip
                val typeChip = findViewById<Chip>(R.id.filter_type)
                typeChip.text = currentTypeFilter ?: getString(R.string.filter_type)

                applyFilters()
            }
            .show()
    }

    private fun showRatingFilterDialog() {
        val ratings = arrayOf("4.5 o más", "4.0 o más", "Mostrar todos")

        AlertDialog.Builder(this)
            .setTitle("Filtrar por Calificación")
            .setItems(ratings) { _, which ->
                val selectedRatingText = ratings[which]

                // 1. Actualiza el estado del filtro
                currentMinRating = when (which) {
                    0 -> 4.5
                    1 -> 4.0
                    else -> null
                }

                // 2. Actualiza el texto del chip
                val ratingChip = findViewById<Chip>(R.id.filter_rating)
                ratingChip.text = if (currentMinRating == null) getString(R.string.filter_rating) else selectedRatingText

                applyFilters()
            }
            .show()
    }

    private fun showCaloriesFilterDialog() {
        val calorieLimits = arrayOf("Menos de 200 kcal", "Menos de 350 kcal", "Mostrar todos")

        AlertDialog.Builder(this)
            .setTitle("Filtrar por Calorías (Máx.)")
            .setItems(calorieLimits) { _, which ->
                val selectedCaloriesText = calorieLimits[which]

                // 1. Actualiza el estado del filtro
                currentMaxCalories = when (which) {
                    0 -> 200
                    1 -> 350
                    else -> null
                }

                // 2. Actualiza el texto del chip
                val caloriesChip = findViewById<Chip>(R.id.filter_calories)
                caloriesChip.text = if (currentMaxCalories == null) getString(R.string.filter_calories) else selectedCaloriesText

                applyFilters()
            }
            .show()
    }

    // =========================================================================
    // II. LÓGICA DE FILTRADO Y RENDERIZADO
    // =========================================================================

    /**
     * Aplica los filtros activos (Type, Rating, Calories) de forma incremental
     * y llama a la función de renderizado con el resultado.
     */
    private fun applyFilters() {
        var filteredList = allRecipes.toList() // Inicia con la lista completa

        // 1. Filtro de Tipo
        currentTypeFilter?.let { type ->
            filteredList = filteredList.filter { it.type == type }
        }

        // 2. Filtro de Rating
        currentMinRating?.let { minRating ->
            filteredList = filteredList.filter { it.getNumericRating() >= minRating }
        }

        // 3. Filtro de Calorías (Máx.)
        currentMaxCalories?.let { maxCalories ->
            filteredList = filteredList.filter { it.getNumericCalories() <= maxCalories }
        }

        // 4. Actualizar la Interfaz con los resultados filtrados
        renderRecipeCards(filteredList)
    }

    /**
     * Infla cada tarjeta de la lista proporcionada y la añade al contenedor.
     */
    private fun renderRecipeCards(recipesToRender: List<HistoricalRecipe>) {
        recipesContainer.removeAllViews()

        if (recipesToRender.isEmpty()) {
            val noResultsView = TextView(this).apply {
                text = "No se encontraron recetas con los filtros seleccionados."
                setTextAppearance(android.R.style.TextAppearance_Material_Medium)
                setPadding(50, 50, 50, 50)
                gravity = android.view.Gravity.CENTER
            }
            recipesContainer.addView(noResultsView)
            return
        }

        recipesToRender.forEach { recipe ->
            val recipeCard = layoutInflater.inflate(
                R.layout.item_historical_recipe,
                recipesContainer,
                false
            )
            setupRecipeCard(recipeCard, recipe)
            recipesContainer.addView(recipeCard)
        }
    }

    // =========================================================================
    // III. LÓGICA DE TARJETAS Y DIÁLOGOS
    // =========================================================================

    /**
     * Configura la lógica de cada tarjeta individual.
     */
    private fun setupRecipeCard(cardView: View, recipe: HistoricalRecipe) {
        // --- 1. Referencias UI ---
        val tvTitle = cardView.findViewById<TextView>(R.id.tv_recipe_title)
        val tvDescription = cardView.findViewById<TextView>(R.id.tv_recipe_description)
        val tvRating = cardView.findViewById<TextView>(R.id.tv_recipe_rating)
        val tvCalories = cardView.findViewById<TextView>(R.id.tv_recipe_calories)
        val tvHistory = cardView.findViewById<TextView>(R.id.tv_recipe_history)
        val tvIngredients = cardView.findViewById<TextView>(R.id.tv_recipe_ingredients)
        val tvPreparation = cardView.findViewById<TextView>(R.id.tv_recipe_preparation)

        val btnShowMore = cardView.findViewById<Button>(R.id.btn_show_more)
        val btnAddReview = cardView.findViewById<Button>(R.id.btn_add_review)
        val expandedContent = cardView.findViewById<LinearLayout>(R.id.expanded_content)

        // --- 2. Seteo de Textos ---
        tvTitle.text = recipe.name
        tvDescription.text = recipe.shortDescription

        tvRating.text = recipe.rating
        tvCalories.text = recipe.calories
        tvHistory.text = recipe.history

        tvIngredients.text = recipe.ingredients.joinToString("\n") { "• $it" }

        val prepText = recipe.preparation.mapIndexed { index, step ->
            "${index + 1}. $step"
        }.joinToString("\n\n")

        tvPreparation.text = prepText

        // --- 3. Expandir/Contraer ---
        var isExpanded = false
        expandedContent.visibility = View.GONE

        btnShowMore.setOnClickListener {
            isExpanded = !isExpanded
            expandedContent.visibility = if (isExpanded) View.VISIBLE else View.GONE
            btnShowMore.text = if (isExpanded) "Mostrar menos ▲" else "Mostrar más ▼"
        }

        // --- 4. Reseñas ---
        btnAddReview.setOnClickListener {
            showReviewDialog(recipe.name)
        }
    }

    /**
     * Muestra un diálogo para añadir una reseña con rank y comentario.
     */
    private fun showReviewDialog(recipeName: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_review, null)
        val etReview = dialogView.findViewById<EditText>(R.id.et_review)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.rating_bar)

        AlertDialog.Builder(this)
            .setTitle("Reseña: $recipeName")
            .setView(dialogView)
            .setPositiveButton("Enviar") { _, _ ->
                val review = etReview.text.toString()
                val rating = ratingBar.rating

                Toast.makeText(
                    this,
                    "Reseña enviada: $rating estrellas para '$recipeName'",
                    Toast.LENGTH_LONG
                ).show()

                // Aquí podrías guardar en Room más adelante
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}