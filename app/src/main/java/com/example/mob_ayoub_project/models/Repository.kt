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

    /**
     * Updates the error message related to email input.
     *
     * <p>This method is used to update the error message that is displayed when
     * there is an issue with the email input, such as during login or registration.
     * The error message is typically shown to the user to inform them of what went wrong.
     *
     * @param message The error message to display related to the email input.
     */
    fun updateErrorMessage(message: String) {
        _emailError.value = message
    }

    /**
     * Updates the message to be shown in a Snackbar.
     *
     * <p>This method is used to update the message that will be displayed in a Snackbar,
     * typically to inform the user of general events or issues, such as whether a recipe
     * is already present in the favorites list. The Snackbar is a brief message shown at
     * the bottom of the screen.
     *
     * @param message The message to display in the Snackbar.
     */
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

    /**
     * Retrieves a favorite recipe by its ID from the database.
     *
     * <p>This method is used to fetch a specific recipe from the user's list of favorite recipes
     * based on the provided recipe ID. It interacts with the local database to perform the retrieval.
     * If the database instance is available, it queries the `RecipeFavoritesDao` to get the recipe.
     * If the database is not initialized or the recipe is not found, the method returns `null`.
     *
     * @param recipeId The unique ID of the recipe to retrieve from the favorites.
     * @return The {@link RecipeFavorite} object corresponding to the given ID, or `null` if the recipe
     *         is not found or the database is unavailable.
     */
    suspend fun getFavoriteRecipeById(recipeId: Int): RecipeFavorite? {
        database?.let { thedatabase ->
            return thedatabase.recipeFavoritesDao().getRecipeById(recipeId)
        }
        return null
    }


}
