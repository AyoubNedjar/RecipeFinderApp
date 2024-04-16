package com.example.mob_ayoub_project

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import com.example.mob_ayoub_project.data.Cuisine
import com.example.mob_ayoub_project.models.AyoubViewModel
import com.example.mob_ayoub_project.models.RecipeViewModel
import com.example.mob_ayoub_project.ui.screens.login.DisplayAboutUser
import com.example.mob_ayoub_project.ui.screens.recipes.SelectCuisineScreen


/**
 * Enumération qui contient les identifiants des diffrentes routes
 */
enum class AyoubScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    He2b(title = R.string.he2b),
    About(title = R.string.about),
    Cuisines(R.string.cuisines)
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
    loginViewModel: AyoubViewModel = viewModel(),
    recipeViewModel : RecipeViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){

    val uiState by loginViewModel.uiState.collectAsState()
    //collects values from view.uiState and wraps them in a state object

    //stores the state of the variable and follow de updates
    var emailError by remember { mutableStateOf("") }

    //stores de current Screen
    var currentScreen by remember { mutableStateOf(AyoubScreen.Cuisines) }

    Scaffold (
        bottomBar = {
            BottomNavigationBar(navController = navController, currentScreen)
        }
    ) {

        //container that uses composable for navigation
        NavHost(
            navController = navController,
            startDestination = AyoubScreen.Cuisines.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)

            ) {

            //le chemin pour le premier écran
            composable(route = AyoubScreen.Start.name) {
                currentScreen = AyoubScreen.Start
                StartConnection(
                    modelView = loginViewModel,
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
                            //
                            loginViewModel.fetchUserInfos()
                            if (loginViewModel.fetchResult.value == AyoubViewModel.ConnectionResult.SUCCES) {
                               // navController.navigate(AyoubScreen.He2b.name)
                                navController.navigate(AyoubScreen.Cuisines.name)
                            }else{
                                emailError = "email ou mot de passe invalide"
                                loginViewModel.resetAll()
                            }
                        }

                    }

                )
            }

            //le chemin pour les infos de l'utilisateur
            composable(route = AyoubScreen.About.name) {
                currentScreen = AyoubScreen.About
                DisplayAboutUser()
            }

            //le chemin pour le deuxième écran
            composable(route = AyoubScreen.He2b.name) {
                currentScreen = AyoubScreen.He2b
                He2bImage()
            }
            
            //le chemin pour les cuisines
            composable(route = AyoubScreen.Cuisines.name) {
                currentScreen = AyoubScreen.Cuisines
                SelectCuisineScreen(
                    cuisines = Cuisine.values().toList(),
                    onSelectionChanged = {
                        recipeViewModel.setCuisine(it)
                        recipeViewModel.fetchRecipesFromCuisine()
                    }
                    )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, currentScreen: AyoubScreen) {
    BottomNavigation{
        if (currentScreen != AyoubScreen.Start) {
            BottomNavigationItem(
                icon  ={
                    Icon(
                        imageVector = Icons.Filled.Home ,
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
        }
    }
}