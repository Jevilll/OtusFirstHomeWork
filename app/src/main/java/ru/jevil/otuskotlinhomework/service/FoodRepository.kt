package ru.jevil.otuskotlinhomework.service

import ru.jevil.otuskotlinhomework.data.Recipe

class FoodRepository(private val foodApi: FoodApi) : BaseRepository() {

    suspend fun getRecipes(): MutableList<Recipe>? {
        val recipesResponse = safeApiCall(
                call = { foodApi.getRecipesAsync().await() },
                errorMessage = "Error fetching recipes"
        )
        return recipesResponse?.recipes?.toMutableList()
    }

    suspend fun getRecipe(id: String): Recipe? {
        val recipeResponse = safeApiCall(
                call = { foodApi.getRecipeAsync(id).await() },
                errorMessage = "Error fetching recipe"
        )
        return recipeResponse?.recipe
    }
}