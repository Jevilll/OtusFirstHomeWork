package ru.jevil.otuskotlinhomework.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.jevil.otuskotlinhomework.data.Recipe
import ru.jevil.otuskotlinhomework.service.ApiFactory
import ru.jevil.otuskotlinhomework.service.FoodRepository
import kotlin.coroutines.CoroutineContext

class CommonViewModel : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : FoodRepository = FoodRepository(ApiFactory.foodApi)

    val recipesLiveData = MutableLiveData<List<RecipeItem>>()
    val recipeLiveData = MutableLiveData<Recipe>()

    fun fetchRecipes() {
        scope.launch {
            val recipes = repository.getRecipes()
            recipesLiveData.postValue(recipes?.map {
                RecipeItem(it.title, it.url, it.id)
            })
        }
    }

    fun fetchRecipe(id: String) {
        scope.launch {
            val recipe = repository.getRecipe(id)
            recipeLiveData.postValue(recipe)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()

}