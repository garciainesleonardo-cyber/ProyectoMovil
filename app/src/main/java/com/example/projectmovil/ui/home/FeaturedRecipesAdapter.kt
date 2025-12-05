package com.example.projectmovil.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmovil.R
import com.example.projectmovil.model.Recipe

class FeaturedRecipeAdapter(
    private var recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<FeaturedRecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.card_recipe)
        val imgRecipe: ImageView = view.findViewById(R.id.img_recipe)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvCategory: TextView = view.findViewById(R.id.tv_category)
        val tvTime: TextView = view.findViewById(R.id.tv_time)
        val tvPrice: TextView = view.findViewById(R.id.tv_price)
        val tvRating: TextView = view.findViewById(R.id.tv_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_featured_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.imgRecipe.setImageResource(recipe.imageResId)
        holder.tvTitle.text = recipe.title
        holder.tvCategory.text = recipe.category
        holder.tvTime.text = "${recipe.timeMinutes} min"
        holder.tvRating.text = String.format("%.1f ★", recipe.rating)

        // ✔️ maneja null, vacío y normal
        val priceText = recipe.price?.takeIf { it.isNotBlank() } ?: "$-"
        holder.tvPrice.text = priceText

        holder.card.setOnClickListener {
            onRecipeClick(recipe)
        }
    }

    fun updateData(newList: List<Recipe>) {
        recipes = newList
        notifyDataSetChanged()
    }
}
