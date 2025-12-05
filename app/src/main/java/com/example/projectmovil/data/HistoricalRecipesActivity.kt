<<<<<<< Updated upstream
package com.example.projectmovil

=======
package com.example.projectmovil.data

<<<<<<<< Updated upstream:app/src/main/java/com/example/projectmovil/data/HistoricalRecipe.kt
// La clase de datos que define la estructura de una receta histórica
data class HistoricalRecipe(
    val name: String,
    val shortDescription: String,
    val rating: String,
    val calories: String,
    val history: String,
    val ingredients: List<String>,
    val preparation: List<String>
)
========
>>>>>>> Stashed changes
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
<<<<<<< Updated upstream
// Importar los componentes de datos
import com.example.projectmovil.data.HistoricalRecipeProvider
import com.example.projectmovil.data.HistoricalRecipe
// Asegúrate de tener los layouts R.layout.activity_historical_recipes, etc.

class HistoricalRecipesActivity : AppCompatActivity() {

    // Obtiene la lista de recetas desde la capa de datos (Provider)
    private val historicalRecipes = HistoricalRecipeProvider.historicalRecipes
=======
import com.example.projectmovil.R
>>>>>>>> Stashed changes:app/src/main/java/com/example/projectmovil/data/HistoricalRecipesActivity.kt

// Objeto Singleton que proporciona la lista de recetas
object HistoricalRecipeProvider {

    val historicalRecipes = listOf(
        HistoricalRecipe(
            "Garum Romano",
            "Salsa de pescado fermentado usada en la antigua Roma",
            "⭐ 4.2",
            "150 kcal por porción",
            "El garum era una salsa de pescado fermentado extremadamente popular en la antigua Roma. Se elaboraba dejando fermentar pescado con sal al sol durante varios meses. Era tan valorado que había diferentes calidades, desde el garum de lujo para patricios hasta versiones más económicas para plebeyos. Se usaba como condimento en prácticamente todos los platos romanos, similar al ketchup moderno.",
            listOf(
                "1 kg de pescado pequeño (anchoas, sardinas)",
                "300g de sal marina",
                "Hierbas aromáticas al gusto",
                "Tiempo de fermentación: 2-3 meses"
            ),
            listOf(
                "Mezclar el pescado con la sal en una proporción 3:1",
                "Colocar en un recipiente de barro al sol",
                "Remover diariamente durante la primera semana",
                "Dejar fermentar 2-3 meses hasta obtener un líquido",
                "Filtrar y almacenar en ánforas selladas",
                "Usar como condimento en carnes, verduras y sopas"
            )
        ),
        HistoricalRecipe(
            "Pottage Medieval",
            "Estofado espeso del medioevo europeo",
            "⭐ 4.5",
            "320 kcal por porción",
            "El pottage era el plato básico de la dieta medieval europea, consumido por todas las clases sociales. Consistía en un estofado espeso de cereales, vegetales y, cuando era posible, carne. Los campesinos lo comían diariamente, mientras que en los castillos se preparaban versiones más elaboradas. Se cocinaba en grandes calderos sobre el fuego y cada familia tenía su receta tradicional transmitida por generaciones.",
            listOf(
                "2 tazas de cebada o avena",
                "3 cebollas grandes",
                "2 nabos",
                "2 zanahorias",
                "Repollo picado",
                "Hierbas medievales (tomillo, salvia)",
                "Caldo de huesos",
                "Sal y pimienta"
            ),
            listOf(
                "Remojar la cebada en agua durante la noche",
                "Picar todas las verduras en trozos grandes",
                "Colocar todo en una olla de hierro con el caldo",
                "Cocinar a fuego lento durante 3-4 horas",
                "Añadir las hierbas en la última hora",
                "Servir caliente en cuencos de madera",
                "Acompañar con pan duro remojado"
            )
        ),
        HistoricalRecipe(
            "Pulque Azteca",
            "Bebida fermentada prehispánica del maguey",
            "⭐ 4.0",
            "110 kcal por vaso",
            "El pulque, conocido como 'octli' en náhuatl, era considerado una bebida sagrada por los aztecas. Solo podía ser consumido libremente por ancianos y en ceremonias religiosas. Se obtiene de la fermentación del aguamiel del maguey y tiene propiedades nutritivas. Los tlachiqueros (recolectores) extraían el aguamiel mediante un proceso tradicional que requería gran habilidad. Era asociado con la diosa Mayahuel y se creía que tenía propiedades medicinales.",
            listOf(
                "Aguamiel fresco de maguey",
                "Semilla de pulque (madre)",
                "Tiempo de fermentación: 24-48 horas"
            ),
            listOf(
                "Raspar el corazón del maguey maduro (8-10 años)",
                "Extraer el aguamiel dos veces al día con un acocote",
                "Verter en tinacales (barriles de madera)",
                "Agregar semilla de pulque para iniciar fermentación",
                "Dejar fermentar 24-48 horas",
                "Servir fresco en jícaras",
                "Consumir el mismo día de su fermentación"
            )
        ),
        HistoricalRecipe(
            "Kumis Mongol",
            "Leche de yegua fermentada de las estepas",
            "⭐ 3.8",
            "90 kcal por vaso",
            "El kumis era la bebida fundamental de los pueblos nómadas de las estepas asiáticas, especialmente los mongoles de Gengis Khan. Se elaboraba fermentando leche de yegua en bolsas de cuero durante varios días, agitándola constantemente. Tenía propiedades embriagantes leves y era considerada medicinal. Los guerreros mongoles la llevaban en sus campañas y se dice que Marco Polo la probó en sus viajes. Era símbolo de hospitalidad y se ofrecía a visitantes importantes.",
            listOf(
                "5 litros de leche de yegua fresca",
                "Cultivo iniciador de kumis anterior",
                "Odre de cuero de caballo"
            ),
            listOf(
                "Ordeñar las yeguas en la mañana",
                "Verter la leche en el odre de cuero",
                "Agregar kumis del día anterior como iniciador",
                "Colgar el odre y agitarlo constantemente durante horas",
                "Dejar fermentar 24 horas",
                "Batir vigorosamente antes de servir",
                "Consumir fresco, preferiblemente en la yurta"
            )
        ),
        HistoricalRecipe(
            "Posca del Legionario",
            "Bebida ácida de los soldados romanos",
            "⭐ 4.3",
            "45 kcal por vaso",
            "La posca era la bebida oficial de los legionarios romanos, una mezcla de vinagre agrio con agua y hierbas. Era barata, fácil de transportar y ayudaba a purificar el agua de calidad dudosa en las campañas militares. Los soldados recibían una ración diaria y se dice que fue lo que se ofreció a Jesús en la cruz. A diferencia del vino puro, la posca podía ser consumida durante el servicio sin causar embriaguez. También tenía propiedades antisépticas.",
            listOf(
                "1 parte de vinagre de vino",
                "3-4 partes de agua",
                "Miel al gusto",
                "Semillas de cilantro machacadas",
                "Hojas de menta fresca"
            ),
            listOf(
                "Mezclar el vinagre con agua en proporción 1:3",
                "Añadir una cucharada de miel para suavizar",
                "Agregar semillas de cilantro machacadas",
                "Incorporar hojas de menta frescas",
                "Dejar reposar 30 minutos para que se mezclen sabores",
                "Servir frío o a temperatura ambiente",
                "Los legionarios lo bebían directamente de sus cantimploras"
            )
        )
    )
<<<<<<<< Updated upstream:app/src/main/java/com/example/projectmovil/data/HistoricalRecipe.kt
}
========
>>>>>>> Stashed changes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical_recipes)

        val btnBack = findViewById<ImageView>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        val recipesContainer = findViewById<LinearLayout>(R.id.recipes_container)

<<<<<<< Updated upstream
        // Itera sobre la lista de recetas para inflar y configurar las tarjetas
        historicalRecipes.forEach { recipe ->
            // Se asume que tienes un layout llamado item_historical_recipe.xml
=======
        historicalRecipes.forEach { recipe ->
>>>>>>> Stashed changes
            val recipeCard = layoutInflater.inflate(R.layout.item_historical_recipe, recipesContainer, false)
            setupRecipeCard(recipeCard, recipe)
            recipesContainer.addView(recipeCard)
        }
    }

    private fun setupRecipeCard(cardView: View, recipe: HistoricalRecipe) {
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

        tvTitle.text = recipe.name
        tvDescription.text = recipe.shortDescription
        tvRating.text = recipe.rating
        tvCalories.text = recipe.calories
        tvHistory.text = recipe.history

<<<<<<< Updated upstream
        // Formato para los ingredientes (con viñetas)
        val ingredientsText = recipe.ingredients.joinToString("\n") { "• $it" }
        tvIngredients.text = ingredientsText

        // Formato para los pasos (con números)
=======
        val ingredientsText = recipe.ingredients.joinToString("\n") { "• $it" }
        tvIngredients.text = ingredientsText

>>>>>>> Stashed changes
        val preparationText = recipe.preparation.mapIndexed { index, step ->
            "${index + 1}. $step"
        }.joinToString("\n\n")
        tvPreparation.text = preparationText

<<<<<<< Updated upstream
        // Lógica de expansión/colapso
=======
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        // Mostrar diálogo de reseña
=======
>>>>>>> Stashed changes
        btnAddReview.setOnClickListener {
            showReviewDialog(recipe.name)
        }
    }

    private fun showReviewDialog(recipeName: String) {
<<<<<<< Updated upstream
        // Se asume que tienes un layout llamado dialog_add_review.xml
=======
>>>>>>> Stashed changes
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
                    "Reseña enviada: $rating estrellas",
                    Toast.LENGTH_SHORT
                ).show()
<<<<<<< Updated upstream
                // TODO: Aquí iría la lógica para guardar la reseña en la base de datos (Room)
=======
                // TODO: Guardar reseña en base de datos
>>>>>>> Stashed changes
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
<<<<<<< Updated upstream
}
=======
}

data class HistoricalRecipe(
    val name: String,
    val shortDescription: String,
    val rating: String,
    val calories: String,
    val history: String,
    val ingredients: List<String>,
    val preparation: List<String>
)
>>>>>>>> Stashed changes:app/src/main/java/com/example/projectmovil/data/HistoricalRecipesActivity.kt
>>>>>>> Stashed changes
