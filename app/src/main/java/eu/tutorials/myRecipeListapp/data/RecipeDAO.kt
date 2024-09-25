package eu.tutorials.myRecipeListapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RecipeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addARecipe(recipeEntity: Recipe)

    @Query("Select * from `Recipe-table`")
    abstract fun getallRecipes(): Flow<List<Recipe>>

    @Update
    abstract suspend fun updateaRecipe(recipeEntity: Recipe)

    @Delete
    abstract suspend fun deleteaRecipe(recipeEntity: Recipe)

    @Query("Select * from `Recipe-table` where id=:id")
    abstract fun getAWishById(id:Long): Flow<Recipe>


}