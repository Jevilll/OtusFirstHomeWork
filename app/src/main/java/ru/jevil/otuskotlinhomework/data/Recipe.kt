package ru.jevil.otuskotlinhomework.data

import com.google.gson.annotations.SerializedName
import java.lang.StringBuilder

data class Recipe(
    @SerializedName("recipe_id") val id: String,
    val publisher: String,
    @SerializedName("image_url") val url: String,
    val title: String,
    val ingredients: List<String>
)

data class RecipesResponse(
        val count: Int,
        val recipes: List<Recipe>,
        val error: String
)

data class RecipeResponse(
        val recipe: Recipe,
        val error: String
)

fun Recipe.getIngredients(): String {
    val recipe = StringBuilder()

    ingredients.forEachIndexed { index, element ->
        recipe.append("${index+1}) $element \n\n")
    }

    return recipe.toString()
}