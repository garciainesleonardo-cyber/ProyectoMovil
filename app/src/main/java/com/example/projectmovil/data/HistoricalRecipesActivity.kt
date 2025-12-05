package com.example.projectmovil

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmovil.data.HistoricalRecipe
import com.example.projectmovil.data.HistoricalRecipeProvider

class HistoricalRecipesActivity : AppCompatActivity() {

    // Lista de recetas obtenida del proveedor (Datos estáticos)
    private val historicalRecipes = HistoricalRecipeProvider.historicalRecipes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ⚠️ Asegúrate de que este layout exista
        setContentView(R.layout.activity_historical_recipes)

        val btnBack = findViewById<ImageView>(R.id.btn_back)
        btnBack.setOnClickListener {
            // Regresa a la actividad anterior
            finish()
        }

        val recipesContainer = findViewById<LinearLayout>(R.id.recipes_container)

        // Lógica para inflar y mostrar cada tarjeta de receta
        historicalRecipes.forEach { recipe ->
            // ⚠️ Asegúrate de que este layout exista
            val recipeCard = layoutInflater.inflate(R.layout.item_historical_recipe, recipesContainer, false)
            setupRecipeCard(recipeCard, recipe)
            recipesContainer.addView(recipeCard)
        }
    }

    /**
     * Configura los datos y la interacción de una tarjeta de receta individual.
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
        // ⚠️ Asegúrate de que este layout exista
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