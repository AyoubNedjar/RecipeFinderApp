package com.example.mob_ayoub_project

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.Image


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mob_ayoub_project.ui.screens.login.He2bImage
import com.example.mob_ayoub_project.ui.screens.login.StartConnection
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.data.InfosFromOneRecipe
import com.example.mob_ayoub_project.data.Ingredients
import com.example.mob_ayoub_project.models.LoginViewModel
import com.example.mob_ayoub_project.models.RecipeViewModel
import com.example.mob_ayoub_project.ui.screens.login.DisplayAboutUser
import com.example.mob_ayoub_project.ui.screens.recipes.AllRecipeFromCuisineScreen
import com.example.mob_ayoub_project.ui.screens.recipes.CreateRecipeScreen
import com.example.mob_ayoub_project.ui.screens.recipes.DisplayFavoritesRecipe
import com.example.mob_ayoub_project.ui.screens.recipes.DisplayRecipeChoosed
import com.example.mob_ayoub_project.ui.screens.recipes.SelectCuisineScreen
import java.net.URLEncoder


/**
 * Enumération qui contient les identifiants des diffrentes routes
 */
enum class AyoubScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    He2b(title = R.string.he2b),
    About(title = R.string.about),
    Cuisines(R.string.cuisines),
    AllRecipe(R.string.allRecipe),
    RecipeChoosed(R.string.theRecipe),
    Favorites(R.string.Favorites),
    CreateRecipe(R.string.recipeCreated),
    RecipeChoosedFromFavorits(R.string.recipeChoosedFromFavorits)
}


/**
 * Function responsible for creating the user interface of the application.
 *
 * @param viewModel Manages the application's data, contains the email.
 * @param navController Manages navigation between the two destinations,
 * the connection page and the He2b page.
 */
@Composable
fun ControlApp(
    loginViewModel: LoginViewModel = viewModel(),
    recipeViewModel : RecipeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){

    val uiState by loginViewModel.uiState.collectAsState()
    //collects values from view.uiState and wraps them in a state object

    //stores the state of the variable and follow de updates
    var emailError by remember { mutableStateOf("") }

    //stores de current Screen
    var currentScreen by remember { mutableStateOf(AyoubScreen.Start) }

    var recipeChoosedFromFavorites  by remember { mutableStateOf(InfosFromOneRecipe()) }

    Scaffold (

        /*
        You can go back when viewing the recipe details after clicking on a recipe from
        the favorites, or when on the screen displaying all the recipes of a cuisine
         */
        topBar = {
                 if(currentScreen != AyoubScreen.Start
                     && currentScreen != AyoubScreen.Favorites
                     && currentScreen != AyoubScreen.He2b
                     && currentScreen != AyoubScreen.About
                     && currentScreen != AyoubScreen.Cuisines){
                     TopAppBar (
                         title = {},
                         navigationIcon = {
                             IconButton(onClick = { navController.popBackStack() }) {
                                 Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                     contentDescription = "back")
                                 
                             }
                         }
                     )

                 }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, currentScreen)
        }
    ) {paddingValues->

        //container that uses composable for navigation
        NavHost(
            navController = navController,
            startDestination = AyoubScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

            ) {

            //Path for the first screen
            composable(route = AyoubScreen.Start.name) {
                currentScreen = AyoubScreen.Start

                val fetchResult by loginViewModel.fetchResult.collectAsState()

                StartConnection(
                    email = uiState.email,
                    emailChange = { loginViewModel.setEmail(it) },
                    emailError = emailError,
                    psw = uiState.password,
                    pswChange = {
                        loginViewModel.setPasswd(it)
                    },
                    onValidateClicked = {
                        //code for check if email is valid
                        if (!loginViewModel.validateEmail(uiState.email)) {
                            emailError = "Adresse e-mail invalide"
                            loginViewModel.resetAll()
                        } else {
                            Log.i("Email validé", uiState.email)
                            loginViewModel.fetchUserInfos()

                        }

                    }

                )

                if (fetchResult == LoginViewModel.ConnectionResult.SUCCES) {
                    navController.navigate(AyoubScreen.Cuisines.name)
                } else if (fetchResult == LoginViewModel.ConnectionResult.ERROR) {
                    emailError = "Email ou mot de passe invalide"

                }
            }


            composable(route = AyoubScreen.About.name) {
                currentScreen = AyoubScreen.About
                DisplayAboutUser()
            }

            composable(route = AyoubScreen.He2b.name) {
                currentScreen = AyoubScreen.He2b
                He2bImage()
            }
            
            //Path for cuisines
            composable(route = AyoubScreen.Cuisines.name) {
                currentScreen = AyoubScreen.Cuisines
                SelectCuisineScreen(
                    cuisines = Cuisine.values().toList(),
                    onSelectionChanged = {
                        recipeViewModel.setCuisine(it)
                        recipeViewModel.fetchRecipesFromCuisine()
                        navController.navigate(AyoubScreen.AllRecipe.name)
                    }
                    )
            }

            //chemin pour voir toutes les recettes d'une cuisine
            composable(route = AyoubScreen.AllRecipe.name){
                currentScreen = AyoubScreen.AllRecipe
                Log.i("Résults of recipes", recipeViewModel.results.toString())
                AllRecipeFromCuisineScreen(
                    allRecipe = recipeViewModel.results.value,
                    onRecipeChoosed = {
                        recipeViewModel.setRecipeChoosed(it)
                        recipeViewModel.fetchInfosFromRecipe()
                        navController.navigate(AyoubScreen.RecipeChoosed.name)
                    } )
            }

            //chemin pour voir la recette selectionnée + possibilité d'ajouter aux favoris
            composable(route=AyoubScreen.RecipeChoosed.name){

                currentScreen = AyoubScreen.RecipeChoosed
                recipeViewModel.resultsInfosFromOneRecipe.value?.let { infosRecipeExist ->
                    DisplayRecipeChoosed(
                        infosRecipeExist,
                        onButtonAddClicked = {theRecipeAddedInFavorits ->
                            recipeViewModel.addFavoriteInTheDatabase(theRecipeAddedInFavorits)
                            navController.navigate(AyoubScreen.Favorites.name)

                        })
                }

            }

            //chemin pour voir quelles sont les recettes favorites
            composable(route=AyoubScreen.Favorites.name){
                currentScreen = AyoubScreen.Favorites

                DisplayFavoritesRecipe(
                    favoritesList = recipeViewModel.favoritesList.value,
                    contentPadding = paddingValues,
                    modifier = Modifier.fillMaxWidth(),
                    onSelectionDeleted = { theRecipeToDelete->
                        recipeViewModel.deleteFavoriteFromTheDatabase(theRecipeToDelete)
                    },

                    /*
                   When a recipe is clicked in the favorites, it should display
                   the details of that recipe, so the DisplayRecipeChoosed function should be reused.
                     */
                    onRecipeClickable = {
                        recipeClicked->
                        recipeChoosedFromFavorites = InfosFromOneRecipe(
                            image = recipeClicked.image,
                            title = recipeClicked.title,
                            veryHealthy = recipeClicked.veryHealthy,
                            summary = recipeClicked.summary,
                            instructions =recipeClicked.instructions,
                            extendedIngredients = recipeClicked.extendedIngredients
                        )

                        navController.navigate(AyoubScreen.RecipeChoosedFromFavorits.name)
                    }

                )

            }

            // Path to view the details of a recipe in the favorites
            composable(route =AyoubScreen.RecipeChoosedFromFavorits.name){
                currentScreen = AyoubScreen.RecipeChoosedFromFavorits
                Log.i("RECIPE_FAVORITS", recipeChoosedFromFavorites.toString())
                DisplayRecipeChoosed(recipe = recipeChoosedFromFavorites)
            }

            // Path to create your own recipe
            composable(route=AyoubScreen.CreateRecipe.name){
                currentScreen = AyoubScreen.CreateRecipe
                CreateRecipeScreen(
                    modifier = Modifier,
                    onButtonClicked = {theRecipeCreated ->
                        recipeViewModel.addFavoriteInTheDatabase(theRecipeCreated)
                        navController.navigate(AyoubScreen.Favorites.name)
                    })

            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, currentScreen: AyoubScreen) {

    BottomNavigation{

        if (currentScreen != AyoubScreen.Start ){

            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home"
                    )
                },
                selected = currentScreen == AyoubScreen.He2b,
                onClick = {
                    navController.navigate(route = AyoubScreen.He2b.name)
                }
            )

            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.kitchen),
                        contentDescription = "Cuisines"
                    )
                },
                selected = currentScreen == AyoubScreen.Cuisines,
                onClick = {
                    navController.navigate(AyoubScreen.Cuisines.name)
                }
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite"
                    )
                },
                selected = currentScreen == AyoubScreen.Favorites,
                onClick = {
                    navController.navigate(route = AyoubScreen.Favorites.name)
                }
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "About"
                    )
                },
                selected = currentScreen == AyoubScreen.About,
                onClick = {
                    navController.navigate(AyoubScreen.About.name)
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Create Recipe"
                    )
                },
                selected = currentScreen == AyoubScreen.CreateRecipe,
                onClick = {
                    navController.navigate(route = AyoubScreen.CreateRecipe.name)
                }
            )
        }



    }
}