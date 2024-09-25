package eu.tutorials.myRecipeListapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tutorials.myRecipeListapp.data.Recipe
import eu.tutorials.myRecipeListapp.data.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val recipeRepository: RecipeRepository = Graph.recipeRepository
):ViewModel() {

    var RecipeTitleState by mutableStateOf("")
    var RecipeDescriptionState by mutableStateOf("")


    fun onWishTitleChanged(newString:String){
        RecipeTitleState = newString
    }

    fun onWishDescriptionChanged(newString: String){
        RecipeDescriptionState = newString
    }

    lateinit var getALLrecipes: Flow<List<Recipe>>

    init {
        viewModelScope.launch {
            getALLrecipes = recipeRepository.getRecipes()
        }
    }

    fun addRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.addARecipe(recipe= recipe)
        }
    }

    fun getArecipeByID(id:Long):Flow<Recipe> {
        return recipeRepository.getARecipeByID(id)
    }

    fun updateRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.updateRecipe(recipe= recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.deleteARecipe(recipe= recipe)
            getALLrecipes = recipeRepository.getRecipes()
        }
    }
}