package com.example.projectmovil.ui.history

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmovil.R
import com.example.projectmovil.data.HistoricalRecipe
import com.example.projectmovil.data.HistoricalRecipeProvider

class HistoricalRecipesActivity : AppCompatActivity() {

    // Lista original (fuente de verdad)
    private val originalRecipes = HistoricalRecipeProvider.historicalRecipes

    // Lista actual filtrada
    private var currentRecipes: List<HistoricalRecipe> = originalRecipes

    // Estado de filtros
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

        // Botón atrás
        findViewById<ImageView>(R.id.btn_back).setOnClickListener { finish() }

        // Configurar filtros
        setupFilterDropdowns()

        // Botón limpiar filtros
        findViewById<Button>(R.id.btn_clear_filters).setOnClickListener {
            clearFilters()
        }

        // Mostrar recetas al inicio
        updateRecipesUI(originalRecipes)
    }

    /** Inicializa los filtros desplegables */
    private fun setupFilterDropdowns() {
        val filterOptions = HistoricalRecipeProvider.getFilterOptions()

        val dropdownType = findViewById<AutoCompleteTextView>(R.id.filter_type_dropdown)
        val dropdownRating = findViewById<AutoCompleteTextView>(R.id.filter_rating_dropdown)
        val dropdownIngredient = findViewById<AutoCompleteTextView>(R.id.filter_ingredient_dropdown)
        val dropdownCalories = findViewById<AutoCompleteTextView>(R.id.filter_calories_dropdown)

        fun setupDropdown(dropdown: AutoCompleteTextView, options: List<String>, updateState: (String?) -> Unit) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                options
            )
            dropdown.setAdapter(adapter)

            dropdown.setOnItemClickListener { parent, _, position, _ ->
                val selectedValue = parent.getItemAtPosition(position).toString()
                updateState(selectedValue)
                applyFilters()
            }

            dropdown.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus && dropdown.text.isNullOrEmpty()) {
                    updateState(null)
                    applyFilters()
                }
            }
        }

        setupDropdown(dropdownType, filterOptions.types) { filterState.selectedType = it }
        setupDropdown(dropdownRating, filterOptions.ratings) { filterState.selectedRating = it }
        setupDropdown(dropdownIngredient, filterOptions.ingredients) { filterState.selectedIngredient = it }
        setupDropdown(dropdownCalories, filterOptions.calories) { filterState.selectedCalories = it }
    }

    /** Aplicar filtros */
    private fun applyFilters() {
        var filtered = originalRecipes

        filterState.selectedType?.let { filtered = filtered.filter { r -> r.type == it } }
        filterState.selectedRating?.let { filtered = filtered.filter { r -> r.rating == it } }
        filterState.selectedIngredient?.let { ingredient ->
            filtered = filtered.filter { r ->
                r.ingredients.any { ing -> ing.contains(ingredient, ignoreCase = true) }
            }
        }
        filterState.selectedCalories?.let { filtered = filtered.filter { r -> r.calories == it } }

        currentRecipes = filtered
        updateRecipesUI(filtered)
    }

    /** Limpia los filtros completamente */
    private fun clearFilters() {
        filterState.selectedType = null
        filterState.selectedRating = null
        filterState.selectedIngredient = null
        filterState.selectedCalories = null

        // Limpiar texto de los dropdowns
        findViewById<AutoCompleteTextView>(R.id.filter_type_dropdown).setText("")
        findViewById<AutoCompleteTextView>(R.id.filter_rating_dropdown).setText("")
        findViewById<AutoCompleteTextView>(R.id.filter_ingredient_dropdown).setText("")
        findViewById<AutoCompleteTextView>(R.id.filter_calories_dropdown).setText("")

        applyFilters()
    }

    /** Renderiza toda la lista */
    private fun updateRecipesUI(recipes: List<HistoricalRecipe>) {
        recipesContainer.removeAllViews()

        if (recipes.isEmpty()) {
            val noData = TextView(this).apply {
                text = "No se encontraron recetas con los filtros seleccionados."
                textSize = 18f
                gravity = android.view.Gravity.CENTER
                setPadding(20, 40, 20, 40)
            }
            recipesContainer.addView(noData)
            return
        }

        recipes.forEach { recipe ->
            val card = layoutInflater.inflate(R.layout.item_historical_recipe, recipesContainer, false)
            setupRecipeCard(card, recipe)
            recipesContainer.addView(card)
        }
    }

    /** Configura cada tarjeta */
    private fun setupRecipeCard(cardView: View, recipe: HistoricalRecipe) {

        val tvTitle = cardView.findViewById<TextView>(R.id.tv_recipe_title)
        val tvDescription = cardView.findViewById<TextView>(R.id.tv_recipe_description)
        val tvRating = cardView.findViewById<TextView>(R.id.tv_recipe_rating)
        val tvCalories = cardView.findViewById<TextView>(R.id.tv_recipe_calories)
        val tvHistory = cardView.findViewById<TextView>(R.id.tv_recipe_history)
        val tvIngredients = cardView.findViewById<TextView>(R.id.tv_recipe_ingredients)
        val tvPreparation = cardView.findViewById<TextView>(R.id.tv_recipe_preparation)
        val btnShowMore = cardView.findViewById<Button>(R.id.btn_show_more)
        val expandedContent = cardView.findViewById<LinearLayout>(R.id.expanded_content)
        val btnAddReview = cardView.findViewById<Button>(R.id.btn_add_review)

        tvTitle.text = recipe.name
        tvDescription.text = recipe.shortDescription
        tvRating.text = recipe.rating
        tvCalories.text = recipe.calories
        tvHistory.text = recipe.history

        tvIngredients.text = recipe.ingredients.joinToString("\n") { "• $it" }

        tvPreparation.text = recipe.preparation.mapIndexed { idx, step ->
            "${idx + 1}. $step"
        }.joinToString("\n\n")

        expandedContent.visibility = View.GONE
        var expanded = false

        btnShowMore.setOnClickListener {
            expanded = !expanded
            if (expanded) {
                expandedContent.visibility = View.VISIBLE
                btnShowMore.text = "Mostrar menos ▲"
            } else {
                expandedContent.visibility = View.GONE
                btnShowMore.text = "Mostrar más ▼"
            }
        }

        btnAddReview.setOnClickListener {
            showReviewDialog(recipe.name)
        }
    }

    /** Diálogo de reseña */
    private fun showReviewDialog(recipeName: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_review, null)
        val etReview = dialogView.findViewById<EditText>(R.id.et_review)
        val ratingBar = dialogView.findViewById<RatingBar>(R.id.rating_bar)

        AlertDialog.Builder(this)
            .setTitle("Reseña: $recipeName")
            .setView(dialogView)
            .setPositiveButton("Enviar") { _, _ ->
                Toast.makeText(
                    this,
                    "Reseña enviada: ${ratingBar.rating} estrellas",
                    Toast.LENGTH_LONG
                ).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
