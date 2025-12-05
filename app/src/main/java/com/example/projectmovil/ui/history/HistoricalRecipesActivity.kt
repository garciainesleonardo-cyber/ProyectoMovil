package com.example.projectmovil.ui.history

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmovil.R
import com.example.projectmovil.data.HistoricalRecipe
import com.example.projectmovil.data.HistoricalRecipeProvider
import com.example.projectmovil.data.FilterOptions

class HistoricalRecipesActivity : AppCompatActivity() {

    // Lista inmutable original (la fuente de verdad para filtrar)
    private val originalRecipes = HistoricalRecipeProvider.historicalRecipes

    // Lista mutable que se mostrará y se actualizará con los filtros
    private var currentRecipes: List<HistoricalRecipe> = originalRecipes

    // Estado actual de las selecciones de filtro
    private data class FilterState(
        var selectedType: String? = null,
        var selectedRating: String? = null,
        var selectedIngredient: String? = null,
        var selectedCalories: String? = null
    )

    private val filterState = FilterState()
    private lateinit var recipesContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical_recipes)

        recipesContainer = findViewById(R.id.recipes_container)
        val btnBack = findViewById<ImageView>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        // --- 1. CONFIGURACIÓN INICIAL DE FILTROS Y UI ---
        setupFilterDropdowns()

        // Mostrar todas las recetas al inicio
        updateRecipesUI(originalRecipes)
    }

    /**
     * Inicializa los AutoCompleteTextView (Desplegables) con las opciones únicas del Provider
     * y configura los listeners de selección.
     */
    private fun setupFilterDropdowns() {
        val filterOptions = HistoricalRecipeProvider.getFilterOptions()

        // Referencias a los Desplegables del XML
        val dropdownType = findViewById<AutoCompleteTextView>(R.id.filter_type_dropdown)
        val dropdownRating = findViewById<AutoCompleteTextView>(R.id.filter_rating_dropdown)
        val dropdownIngredient = findViewById<AutoCompleteTextView>(R.id.filter_ingredient_dropdown)
        val dropdownCalories = findViewById<AutoCompleteTextView>(R.id.filter_calories_dropdown)

        // Función auxiliar para crear adaptadores y configurar listeners
        fun setupDropdown(dropdown: AutoCompleteTextView, options: List<String>, updateState: (String?) -> Unit) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                options
            )
            dropdown.setAdapter(adapter)

            // Listener para aplicar filtro al seleccionar
            dropdown.setOnItemClickListener { parent, _, position, _ ->
                val selectedValue = parent.getItemAtPosition(position).toString()
                updateState(selectedValue) // Actualiza la propiedad del FilterState
                applyFilters()            // Ejecuta el filtrado
            }

            // Listener para detectar cuando se borra la selección (si el usuario borra el texto)
            dropdown.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus && dropdown.text.isNullOrEmpty()) {
                    updateState(null) // Borra la propiedad del FilterState
                    applyFilters()
                }
            }
        }

        // Aplicar la configuración a cada filtro
        setupDropdown(dropdownType, filterOptions.types) { filterState.selectedType = it }
        setupDropdown(dropdownRating, filterOptions.ratings) { filterState.selectedRating = it }
        setupDropdown(dropdownIngredient, filterOptions.ingredients) { filterState.selectedIngredient = it }
        setupDropdown(dropdownCalories, filterOptions.calories) { filterState.selectedCalories = it }
    }

    /**
     * Filtra la lista original de recetas basándose en el estado actual de los desplegables.
     */
    private fun applyFilters() {
        var filteredList: List<HistoricalRecipe> = originalRecipes

        // 1. Filtrar por Tipo
        filterState.selectedType?.let { type ->
            filteredList = filteredList.filter { it.type == type }
        }

        // 2. Filtrar por Calificación
        filterState.selectedRating?.let { rating ->
            filteredList = filteredList.filter { it.rating == rating }
        }

        // 3. Filtrar por Ingrediente
        filterState.selectedIngredient?.let { ingredient ->
            // Busca si alguno de los ingredientes de la receta contiene el ingrediente seleccionado
            filteredList = filteredList.filter { recipe ->
                // NOTA: Esta lógica asume que el ingrediente en la lista de la receta es fácil de buscar.
                // Si la limpieza en el Provider no fue perfecta, esta búsqueda puede fallar.
                recipe.ingredients.any { rawIngredient ->
                    rawIngredient.contains(ingredient, ignoreCase = true)
                }
            }
        }

        // 4. Filtrar por Calorías
        filterState.selectedCalories?.let { calories ->
            filteredList = filteredList.filter { it.calories == calories }
        }

        // Actualizar la UI con los resultados
        currentRecipes = filteredList
        updateRecipesUI(currentRecipes)
    }

    /**
     * Borra el contenido actual del contenedor de recetas y lo repinta con la nueva lista.
     */
    private fun updateRecipesUI(recipes: List<HistoricalRecipe>) {
        recipesContainer.removeAllViews() // Borrar todas las vistas de recetas anteriores

        if (recipes.isEmpty()) {
            // Muestra un mensaje si no hay resultados
            val noResults = TextView(this).apply {
                text = "No se encontraron recetas con los filtros seleccionados."
                setPadding(32, 32, 32, 32)
                gravity = android.view.Gravity.CENTER
                textSize = 18f
                setTextColor(resources.getColor(R.color.text_secondary, theme))
            }
            recipesContainer.addView(noResults)
        } else {
            // Infla y añade las nuevas tarjetas de recetas
            recipes.forEach { recipe ->
                val recipeCard = layoutInflater.inflate(R.layout.item_historical_recipe, recipesContainer, false)
                setupRecipeCard(recipeCard, recipe)
                recipesContainer.addView(recipeCard)
            }
        }
    }

    /**
     * Configura los datos y la interacción de una tarjeta de receta individual (MANTENIDA IGUAL).
     */
    private fun setupRecipeCard(cardView: View, recipe: HistoricalRecipe) {
        // --- 1. Encontrar Vistas ---
        val tvTitle = cardView.findViewById<TextView>(R.id.tv_recipe_title)
        val tvDescription = cardView.findViewById<TextView>(R.id.tv_recipe_description)
        val tvRating = cardView.findViewById<TextView>(R.id.tv_recipe_rating)
        val tvCalories = cardView.findViewById<TextView>(R.id.tv_recipe_calories)
        val tvHistory = cardView.findViewById<TextView>(R.id.tv_recipe_history)
        val tvIngredients = cardView.findViewById<TextView>(R.id.tv_recipe_ingredients)
        val tvPreparation = cardView.findViewById<TextView>(R.id.tv_recipe_preparation)
        val btnShowMore = cardView.findViewById<Button>(R.id.btn_show_more)
        val btnAddReview = cardView.findViewById<Button>(R.id.btn_add_review)
        val expandedContent = cardView.findViewById<LinearLayout>(R.id.expanded_content) // Contenido oculto

        // --- 2. Asignar Texto ---
        tvTitle.text = recipe.name
        tvDescription.text = recipe.shortDescription
        tvRating.text = recipe.rating
        tvCalories.text = recipe.calories
        tvHistory.text = recipe.history

        // Formatear listas a texto con bullet points
        val ingredientsText = recipe.ingredients.joinToString("\n") { "• $it" }
        tvIngredients.text = ingredientsText

        // Formatear pasos con numeración
        val preparationText = recipe.preparation.mapIndexed { index, step ->
            "${index + 1}. $step"
        }.joinToString("\n\n")
        tvPreparation.text = preparationText

        // --- 3. Lógica de Expansión/Contracción ---
        var isExpanded = false
        expandedContent.visibility = View.GONE

        btnShowMore.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                expandedContent.visibility = View.VISIBLE
                btnShowMore.text = "Mostrar menos ▲"
            } else {
                expandedContent.visibility = View.GONE
                btnShowMore.text = "Mostrar más ▼"
            }
        }

        // --- 4. Lógica del diálogo de Reseña ---
        btnAddReview.setOnClickListener {
            showReviewDialog(recipe.name)
        }
    }

    /**
     * Muestra el diálogo para añadir una reseña y maneja el Toast de confirmación.
     */
    private fun showReviewDialog(recipeName: String) {
        // Asegúrate de que este layout exista: dialog_add_review.xml
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_review, null)
        val etReview = dialogView.findViewById<EditText>(R.id.et_review)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.rating_bar)

        AlertDialog.Builder(this)
            .setTitle("Reseña: $recipeName")
            .setView(dialogView)
            .setPositiveButton("Enviar") { _, _ ->
                val review = etReview.text.toString()
                val rating = ratingBar.rating

                // Muestra la confirmación (Aquí iría la lógica de Room si la tuvieras)
                Toast.makeText(
                    this,
                    "Reseña enviada: $rating estrellas para '$recipeName'",
                    Toast.LENGTH_LONG
                ).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}