package eu.tutorials.myRecipeListapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Recipe-table")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name="Recipe-title")
    val title: String="",
    @ColumnInfo(name="Recipe-desc")
    val description:String=""
)
/*

object DummyRecipe{
    val RecipeList = listOf(
        Recipe(title="Biryani",
            description =  "add meat")
        )
}
*/
