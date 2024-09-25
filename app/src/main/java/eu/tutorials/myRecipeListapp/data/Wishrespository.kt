package eu.tutorials.myRecipeListapp.data

import kotlinx.coroutines.flow.Flow


class RecipeRepository(private val recipeDAO: RecipeDAO) {

    suspend fun addARecipe(recipe:Recipe){
        recipeDAO.addARecipe(recipe)
    }

    fun getRecipes(): Flow<List<Recipe>> = recipeDAO.getallRecipes()

    fun getARecipeByID(id:Long) :Flow<Recipe> {
        return recipeDAO.getAWishById(id)
    }

    suspend fun updateRecipe(recipe:Recipe){
        recipeDAO.updateaRecipe(recipe)
    }

    suspend fun deleteARecipe(recipe: Recipe){
        recipeDAO.deleteaRecipe(recipe)
    }

}