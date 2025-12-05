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
    private val recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<FeaturedRecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardContainer: CardView = itemView.findViewById(R.id.card_recipe)
        val image: ImageView = itemView.findViewById(R.id.iv_recipe_image)
        val title: TextView = itemView.findViewById(R.id.tv_recipe_title)
        val category: TextView = itemView.findViewById(R.id.tv_recipe_category)
        val rating: TextView = itemView.findViewById(R.id.tv_recipe_rating)
        val time: TextView = itemView.findViewById(R.id.tv_recipe_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_featured_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.title.text = recipe.title
        holder.category.text = recipe.category
        holder.rating.text = String.format("%.1f ★", recipe.rating)
        holder.time.text = "${recipe.timeMinutes} min"
        holder.image.setImageResource(recipe.imageResId)

        holder.cardContainer.setOnClickListener {
            onItemClick(recipe)
        }
    }
}
