import android.content.Context
import com.example.mob_ayoub_project.R
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.database.FavoritesDatabase
import com.example.mob_ayoub_project.database.RecipeFavorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Repository.kt

object Repository {

    private var database: FavoritesDatabase? = null
    private val _messageSnackBar = MutableStateFlow<String>("")
    val messageSnackBar: StateFlow<String> get() = _messageSnackBar

    private val _emailError = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> get() = _emailError

    fun initDatabase(context: Context) {
        if (database == null) {
            database = FavoritesDatabase.getInstance(context)
        }
    }

    fun updateErrorMessage(message: String) {
        _emailError.value = message
    }

    fun updateMessageSnackBar(message: String) {
        _messageSnackBar.value = message
    }

    suspend fun insertFavoriteInDatabase(recipe: InfosFromOneRecipe) {
        database?.let { thedatabase ->
            val newRecipe = RecipeFavorite(
                0, recipe.image, recipe.title, recipe.veryHealthy,
                recipe.summary, recipe.instructions, recipe.extendedIngredients
            )
            thedatabase.recipeFavoritesDao().addNewRecipe(newRecipe)
        }
    }

    suspend fun getAllFavoritesRecipe(): List<RecipeFavorite> {
        database?.let { thedatabase ->
            return thedatabase.recipeFavoritesDao().getAllRecipe()
        }
        return listOf()
    }

    suspend fun removeFavoriteFromDatabase(recipe: RecipeFavorite) {
        database?.let { thedatabase ->
            thedatabase.recipeFavoritesDao().removeFavorite(recipe)
        }
    }

    suspend fun addOrShowMessage(context: Context, recipe: InfosFromOneRecipe) {
        val allFavorites = getAllFavoritesRecipe()
        if (allFavorites.any { it.title == recipe.title }) {
            updateMessageSnackBar(context.getString(R.string.alreadyFavorites))
        } else {
            insertFavoriteInDatabase(recipe)
            updateMessageSnackBar(context.getString(R.string.newFavorites))
        }
    }

    suspend fun getFavoriteRecipeById(recipeId: Int): RecipeFavorite? {
        database?.let { thedatabase ->
            return thedatabase.recipeFavoritesDao().getRecipeById(recipeId)
        }
        return null
    }

    suspend fun showAlertForWifiOrNot() {
        updateMessageSnackBar("")
    }
}
