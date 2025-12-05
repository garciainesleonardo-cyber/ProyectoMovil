package com.example.projectmovil.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmovil.R
import com.example.projectmovil.model.Recipe
import com.example.projectmovil.ui.history.HistoricalRecipesActivity
import com.example.projectmovil.ui.recipe.RecipeDetailActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var rvFeaturedRecipes: RecyclerView
    private lateinit var adapter: FeaturedRecipeAdapter
    private lateinit var allRecipes: List<Recipe>

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

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        val rvCategories = findViewById<RecyclerView>(R.id.rv_categories)
        rvCategories.layoutManager = GridLayoutManager(this, 4)

        // 🔹 Aquí llenamos las recetas destacadas
        setupFeaturedRecipes()

        // Click listeners para iconos
        iconFilter.setOnClickListener {
            Toast.makeText(this, "Filtros - Próximamente", Toast.LENGTH_SHORT).show()
        }
=======
        // 🔹 Recetas de prueba
        setupFeaturedRecipes()

        // 🔹 Iconos
        iconFilter.setOnClickListener { showFilterDialog() }
>>>>>>> Stashed changes
=======
        // 🔹 Recetas de prueba
        setupFeaturedRecipes()

        // 🔹 Iconos
        iconFilter.setOnClickListener { showFilterDialog() }
>>>>>>> Stashed changes
=======
        // 🔹 Recetas de prueba
        setupFeaturedRecipes()

        // 🔹 Iconos
        iconFilter.setOnClickListener { showFilterDialog() }
>>>>>>> Stashed changes

        iconCart.setOnClickListener {
            Toast.makeText(this, "Carrito - Próximamente", Toast.LENGTH_SHORT).show()
        }

        iconNotifications.setOnClickListener {
            Toast.makeText(this, "Notificaciones - Próximamente", Toast.LENGTH_SHORT).show()
        }

        searchBar.setOnClickListener {
            Toast.makeText(this, "Búsqueda - Próximamente", Toast.LENGTH_SHORT).show()
        }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        // Navegación a categorías
=======
        iconProfile.setOnClickListener { showProfileMenu() }

>>>>>>> Stashed changes
=======
        iconProfile.setOnClickListener { showProfileMenu() }

>>>>>>> Stashed changes
=======
        iconProfile.setOnClickListener { showProfileMenu() }

>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    // 🔹 NUEVA FUNCIÓN: crea recetas fake y conecta el adapter
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    // 🔹 Menú de cuenta (solo cerrar sesión por ahora)
    private fun showProfileMenu() {
        val options = arrayOf("Cerrar sesión", "Cancelar")

        AlertDialog.Builder(this)
            .setTitle("Cuenta")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> logout()
                    1 -> dialog.dismiss()
                }
            }
            .show()
    }

    private fun logout() {
        Toast.makeText(this, "Cerrando sesión...", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }

    // 🔹 Recetas de prueba con ingredientes y costo
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    private fun setupFeaturedRecipes() {
        allRecipes = listOf(
            Recipe(
                id = 1,
                title = "Ensalada Mediterránea",
                category = "Saludable",
                description = "Ensalada fresca con jitomate, pepino, aceitunas, queso feta y aceite de oliva.",
                timeMinutes = 15,
                price = "$80 MXN aprox.",
                rating = 4.8f,
                imageResId = R.mipmap.ic_launcher,
                ingredients = listOf("jitomate", "pepino", "aceitunas", "queso feta", "aceite de oliva"),
                costLevel = 1   // Bajo
            ),
            Recipe(
                id = 2,
                title = "Pollo a la plancha con verduras",
                category = "Proteínas",
                description = "Pechuga de pollo marinada con especias, salteada con verduras mixtas.",
                timeMinutes = 25,
                price = "$110 MXN aprox.",
                rating = 4.5f,
                imageResId = R.mipmap.ic_launcher,
                ingredients = listOf("pollo", "zanahoria", "brócoli", "calabaza", "aceite"),
                costLevel = 2   // Medio
            ),
            Recipe(
                id = 3,
                title = "Tostadas de atún fit",
                category = "Rápido",
                description = "Tostadas horneadas con mezcla de atún, jitomate, cebolla y limón.",
                timeMinutes = 10,
                price = "$60 MXN aprox.",
                rating = 4.2f,
                imageResId = R.mipmap.ic_launcher,
                ingredients = listOf("atún", "jitomate", "cebolla", "limón", "tostadas"),
                costLevel = 1   // Bajo
            ),
            Recipe(
                id = 4,
                title = "Pasta cremosa de champiñones",
                category = "Cena",
                description = "Pasta integral con salsa cremosa ligera de champiñones.",
                timeMinutes = 30,
                price = null,   // sin precio -> se muestra $- en el adapter
                rating = 4.6f,
                imageResId = R.mipmap.ic_launcher,
                ingredients = listOf("pasta", "champiñones", "leche", "ajo", "cebolla"),
                costLevel = 2   // Medio
            ),
            Recipe(
                id = 5,
                title = "Smoothie verde detox",
                category = "Bebidas",
                description = "Smoothie de espinaca, piña, manzana y jengibre.",
                timeMinutes = 5,
                price = "$40 MXN aprox.",
                rating = 4.9f,
                imageResId = R.mipmap.ic_launcher,
                ingredients = listOf("espinaca", "piña", "manzana", "jengibre", "agua"),
                costLevel = 1   // Bajo
            )
        )

        adapter = FeaturedRecipeAdapter(allRecipes) { recipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("recipe", recipe)
            startActivity(intent)
        }

        rvFeaturedRecipes.adapter = adapter
    }

    // 🔹 Diálogo para aplicar filtros
    private fun showFilterDialog() {
        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_recipe_filters, null, false)

        val spMaxTime = dialogView.findViewById<Spinner>(R.id.sp_max_time)
        val spCost = dialogView.findViewById<Spinner>(R.id.sp_cost)
        val etIngredient = dialogView.findViewById<EditText>(R.id.et_ingredient)
        val rbInclude = dialogView.findViewById<RadioButton>(R.id.rb_include)
        val rbExclude = dialogView.findViewById<RadioButton>(R.id.rb_exclude)

        // Opciones de tiempo
        val timeOptions = arrayOf(
            "Cualquiera",
            "Hasta 15 min",
            "Hasta 30 min",
            "Hasta 45 min"
        )
        spMaxTime.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, timeOptions)

        // Opciones de costo
        val costOptions = arrayOf(
            "Todos",
            "Bajo",
            "Medio",
            "Alto"
        )
        spCost.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, costOptions)

        AlertDialog.Builder(this)
            .setTitle("Filtros")
            .setView(dialogView)
            .setPositiveButton("Aplicar") { dialog, _ ->
                val selectedTimePos = spMaxTime.selectedItemPosition
                val selectedCost = spCost.selectedItem.toString()
                val ingredient = etIngredient.text.toString().trim().lowercase()
                val exclude = rbExclude.isChecked

                val maxTime: Int? = when (selectedTimePos) {
                    1 -> 15
                    2 -> 30
                    3 -> 45
                    else -> null
                }

                applyFilters(maxTime, selectedCost, ingredient, exclude)
                dialog.dismiss()
            }
            .setNegativeButton("Limpiar") { dialog, _ ->
                adapter.updateData(allRecipes)   // quitar filtros
                dialog.dismiss()
            }
            .show()
    }

    // 🔹 Lógica de filtros
    private fun applyFilters(
        maxTime: Int?,
        costFilter: String,
        ingredientText: String,
        exclude: Boolean
    ) {
        var filtered = allRecipes

        // Tiempo máximo
        maxTime?.let { limit ->
            filtered = filtered.filter { it.timeMinutes <= limit }
        }

        // Costo
        val costLevelFilter: Int? = when (costFilter) {
            "Bajo" -> 1
            "Medio" -> 2
            "Alto" -> 3
            else -> null
        }

        costLevelFilter?.let { level ->
            filtered = filtered.filter { it.costLevel == level }
        }

        // Ingredientes (incluir / excluir)
        if (ingredientText.isNotBlank()) {
            filtered = if (exclude) {
                filtered.filter { recipe ->
                    recipe.ingredients.none { ing ->
                        ing.contains(ingredientText, ignoreCase = true)
                    }
                }
            } else {
                filtered.filter { recipe ->
                    recipe.ingredients.any { ing ->
                        ing.contains(ingredientText, ignoreCase = true)
                    }
                }
            }
        }

        adapter.updateData(filtered)

        if (filtered.isEmpty()) {
            Toast.makeText(
                this,
                "No hay recetas que coincidan con los filtros",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
