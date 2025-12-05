package com.example.projectmovil.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmovil.R
import com.example.projectmovil.model.Recipe
import com.example.projectmovil.ui.history.HistoricalRecipesActivity
import com.example.projectmovil.ui.recipe.RecipeDetailActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var rvFeaturedRecipes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userEmail = intent.getStringExtra("USER_EMAIL")
        val userName = userEmail?.substringBefore("@") ?: "Usuario"

        val tvWelcome = findViewById<TextView>(R.id.tv_welcome)
        val tvUserName = findViewById<TextView>(R.id.tv_user_name)
        val searchBar = findViewById<EditText>(R.id.et_search)
        val iconFilter = findViewById<ImageView>(R.id.icon_filter)
        val iconCart = findViewById<ImageView>(R.id.icon_cart)
        val iconNotifications = findViewById<ImageView>(R.id.icon_notifications)

        tvWelcome.text = "Bienvenido"
        tvUserName.text = userName.replaceFirstChar { it.uppercase() }

        rvFeaturedRecipes = findViewById(R.id.rv_featured_recipes)
        rvFeaturedRecipes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val rvCategories = findViewById<RecyclerView>(R.id.rv_categories)
        rvCategories.layoutManager = GridLayoutManager(this, 4)

        // 🔹 Aquí llenamos las recetas destacadas
        setupFeaturedRecipes()

        // Click listeners para iconos
        iconFilter.setOnClickListener {
            Toast.makeText(this, "Filtros - Próximamente", Toast.LENGTH_SHORT).show()
        }

        iconCart.setOnClickListener {
            Toast.makeText(this, "Carrito - Próximamente", Toast.LENGTH_SHORT).show()
        }

        iconNotifications.setOnClickListener {
            Toast.makeText(this, "Notificaciones - Próximamente", Toast.LENGTH_SHORT).show()
        }

        searchBar.setOnClickListener {
            Toast.makeText(this, "Búsqueda - Próximamente", Toast.LENGTH_SHORT).show()
        }

        // Navegación a categorías
        setupCategoryNavigation()
    }

    private fun setupCategoryNavigation() {
        val historicalRecipesCard = findViewById<CardView>(R.id.card_historical)

        historicalRecipesCard?.setOnClickListener {
            val intent = Intent(this, HistoricalRecipesActivity::class.java)
            startActivity(intent)
        }

        val proteinsCard = findViewById<CardView>(R.id.card_proteins)
        val dietCard = findViewById<CardView>(R.id.card_diet)
        val botCard = findViewById<CardView>(R.id.card_bot)
        val healthCard = findViewById<CardView>(R.id.card_health)

        proteinsCard?.setOnClickListener {
            Toast.makeText(this, "Recetas por Proteína - Próximamente", Toast.LENGTH_SHORT).show()
        }

        dietCard?.setOnClickListener {
            Toast.makeText(this, "Recetas por Dieta - Próximamente", Toast.LENGTH_SHORT).show()
        }

        botCard?.setOnClickListener {
            Toast.makeText(this, "Bot Chef - Próximamente", Toast.LENGTH_SHORT).show()
        }

        healthCard?.setOnClickListener {
            Toast.makeText(this, "Recetas Saludables - Próximamente", Toast.LENGTH_SHORT).show()
        }
    }

    // 🔹 NUEVA FUNCIÓN: crea recetas fake y conecta el adapter
    private fun setupFeaturedRecipes() {
        val recipes = listOf(
            Recipe(
                id = 1,
                title = "Ensalada Mediterránea",
                category = "Saludable",
                description = "Una ensalada fresca con jitomate, pepino, aceitunas, queso feta y aderezo de aceite de oliva.",
                timeMinutes = 15,
                price = "$80 MXN aprox.",
                rating = 4.8f,
                imageResId = R.mipmap.ic_launcher
            ),
            Recipe(
                id = 2,
                title = "Pollo a la Plancha con Verduras",
                category = "Proteínas",
                description = "Pechuga de pollo marinada con especias, acompañada de verduras salteadas.",
                timeMinutes = 25,
                price = "$110 MXN aprox.",
                rating = 4.5f,
                imageResId = R.mipmap.ic_launcher
            ),
            Recipe(
                id = 3,
                title = "Tostadas de Atún Fit",
                category = "Rápido",
                description = "Tostadas horneadas con mezcla de atún, verduras y un toque de limón.",
                timeMinutes = 10,
                price = "$60 MXN aprox.",
                rating = 4.2f,
                imageResId = R.mipmap.ic_launcher
            )
        )

        val adapter = FeaturedRecipeAdapter(recipes) { recipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("recipe", recipe)
            startActivity(intent)
        }

        rvFeaturedRecipes.adapter = adapter
    }
}
