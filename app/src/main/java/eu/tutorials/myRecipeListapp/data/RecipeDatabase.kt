package eu.tutorials.myRecipeListapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [Recipe::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDAO
}