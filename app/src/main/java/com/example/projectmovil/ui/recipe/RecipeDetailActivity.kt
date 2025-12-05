package com.example.projectmovil.ui.recipe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmovil.R
import com.example.projectmovil.model.Recipe

class RecipeDetailActivity : AppCompatActivity() {

    private var currentRating: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        // Receta que viene desde el Home
        val recipe = intent.getSerializableExtra("recipe") as? Recipe
        if (recipe == null) {
            Toast.makeText(this, "Error al cargar receta", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        currentRating = recipe.rating

        // --- Views principales ---
        val ivImage = findViewById<ImageView>(R.id.iv_detail_image)
        val tvTitle = findViewById<TextView>(R.id.tv_detail_title)
        val tvCategory = findViewById<TextView>(R.id.tv_detail_category)
        val tvRating = findViewById<TextView>(R.id.tv_detail_rating)
        val tvTime = findViewById<TextView>(R.id.tv_detail_time)
        val tvPrice = findViewById<TextView>(R.id.tv_detail_price)
        val tvDescription = findViewById<TextView>(R.id.tv_detail_description)

        // --- Nuevas vistas para detalle completo ---
        val tvIngredients = findViewById<TextView>(R.id.tv_detail_ingredients)
        val tvSteps = findViewById<TextView>(R.id.tv_detail_steps)
        val tvServings = findViewById<TextView>(R.id.tv_detail_servings)
        val tvDifficulty = findViewById<TextView>(R.id.tv_detail_difficulty)

        // Botones
        val btnSave = findViewById<Button>(R.id.btn_save_recipe)
        val btnShare = findViewById<Button>(R.id.btn_share_recipe)
        val btnAddReview = findViewById<Button>(R.id.btn_add_review)

        // Botón de regreso (icono arriba a la izquierda)
        findViewById<ImageView?>(R.id.btn_back)?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Precio formateado con fallback "$-"
        val formattedPrice = recipe.price?.takeIf { it.isNotBlank() } ?: "$-"

        // --- Llenar datos básicos ---
        ivImage.setImageResource(recipe.imageResId)
        tvTitle.text = recipe.title
        tvCategory.text = recipe.category
        tvRating.text = String.format("%.1f ★", currentRating)
        tvTime.text = "${recipe.timeMinutes} min"
        tvPrice.text = formattedPrice
        tvDescription.text = recipe.description

        // --- Ingredientes (en viñetas) ---
        tvIngredients.text =
            if (recipe.ingredients.isNotEmpty()) {
                "Ingredientes:\n" + recipe.ingredients.joinToString(
                    separator = "\n• ",
                    prefix = "• "
                )
            } else {
                "Ingredientes: no registrados."
            }

        // --- Pasos de preparación (1., 2., 3., ...) ---
        tvSteps.text =
            if (recipe.steps.isNotEmpty()) {
                "Preparación:\n" + recipe.steps.mapIndexed { index, step ->
                    "${index + 1}. $step"
                }.joinToString("\n")
            } else {
                "Preparación: pasos no registrados."
            }

        // --- Porciones y dificultad ---
        tvServings.text = "Porciones: ${recipe.servings}"
        tvDifficulty.text = "Dificultad: ${recipe.difficulty}"

        // Guardar (placeholder)
        btnSave.setOnClickListener {
            Toast.makeText(this, "Receta guardada (placeholder)", Toast.LENGTH_SHORT).show()
        }

        // Compartir con info más completa
        btnShare.setOnClickListener {
            val ingredientsText =
                if (recipe.ingredients.isNotEmpty())
                    recipe.ingredients.joinToString(", ")
                else
                    "No registrados"

            val shareText = """
                Mira esta receta: ${recipe.title}
                
                Categoría: ${recipe.category}
                Tiempo: ${recipe.timeMinutes} min
                Precio aprox: $formattedPrice
                Porciones: ${recipe.servings}
                Dificultad: ${recipe.difficulty}
                
                Ingredientes: $ingredientsText
                
                ${recipe.description}
            """.trimIndent()

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, recipe.title)
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            startActivity(Intent.createChooser(intent, "Compartir receta"))
        }

        // Añadir reseña
        btnAddReview.setOnClickListener {
            showReviewDialog(tvRating)
        }
    }

    private fun showReviewDialog(tvRating: TextView) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_review_recipe, null)

        val ratingBar = dialogView.findViewById<RatingBar>(R.id.rating_bar)
        val etComment = dialogView.findViewById<EditText>(R.id.et_review_comment)

        AlertDialog.Builder(this)
            .setTitle("Añadir reseña")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val newRating = ratingBar.rating
                if (newRating > 0f) {
                    currentRating = newRating
                    tvRating.text = String.format("%.1f ★", currentRating)
                    Toast.makeText(
                        this,
                        "Gracias por tu reseña: ${etComment.text}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(this, "Selecciona al menos 1 estrella", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
