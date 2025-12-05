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
import com.example.projectmovil.ui.history.HistoricalRecipesActivity
import com.example.projectmovil.R

class HomeActivity : AppCompatActivity() {

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

        val rvFeaturedRecipes = findViewById<RecyclerView>(R.id.rv_featured_recipes)
        rvFeaturedRecipes.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val rvCategories = findViewById<RecyclerView>(R.id.rv_categories)
        rvCategories.layoutManager = GridLayoutManager(this, 4)

        // TODO: Aquí conectarás los adapters cuando tengas los datos


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
        // Por ahora, solo implementamos la navegación a Recetas Históricas
        // Los demás se implementarán después

        val historicalRecipesCard = findViewById<CardView>(R.id.card_historical)

        historicalRecipesCard?.setOnClickListener {
            val intent = Intent(this, HistoricalRecipesActivity::class.java)
            startActivity(intent)
        }

        // Placeholder para las demás categorías
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
}