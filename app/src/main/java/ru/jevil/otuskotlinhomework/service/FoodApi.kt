package ru.jevil.otuskotlinhomework.service

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.jevil.otuskotlinhomework.data.Recipe
import ru.jevil.otuskotlinhomework.data.RecipeResponse
import ru.jevil.otuskotlinhomework.data.RecipesResponse

interface FoodApi {
    @GET("search")
    fun getRecipesAsync(): Deferred<Response<RecipesResponse>>

    @GET("get")
    fun getRecipeAsync(@Query("rId") id: String): Deferred<Response<RecipeResponse>>
}