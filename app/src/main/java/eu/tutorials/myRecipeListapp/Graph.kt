package eu.tutorials.myRecipeListapp

import android.content.Context
import androidx.room.Room
import eu.tutorials.myRecipeListapp.data.RecipeDatabase
import eu.tutorials.myRecipeListapp.data.RecipeRepository

object Graph {
    lateinit var database: RecipeDatabase

    val recipeRepository by lazy{
        RecipeRepository(recipeDAO = database.recipeDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, RecipeDatabase::class.java, "Recipe.db").build()
    }

}