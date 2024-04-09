package com.example.mob_ayoub_project

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mob_ayoub_project.ui.screens.He2bImage
import com.example.mob_ayoub_project.ui.screens.StartConnection
import com.example.mob_ayoub_project.ui.screens.displayAboutUser


/**
 * Enumération qui contient les identifiants des diffrentes routes
 */
enum class AyoubScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    He2b(title = R.string.he2b),
    About(title = R.string.about)
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
    viewModel: AyoubViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){

    val uiState by viewModel.uiState.collectAsState()
    //collects values from view.uiState and wraps them in a state object

    //stores the state of the variable and follow de updates
    var emailError by remember { mutableStateOf("") }


        //container that uses composable for navigation
        NavHost(
            navController = navController,
            startDestination = AyoubScreen.Start.name,
            modifier = Modifier
                .fillMaxSize(),


        ){


            //le chemin pour le premier écran
            composable(route = AyoubScreen.Start.name){
                StartConnection(
                    email = uiState.email,
                    emailChange = {viewModel.setEmail(it)},
                    emailError  = emailError,
                    psw = uiState.password,
                    pswChange = {
                        viewModel.setPasswd(it)
                    },

                    onValidateClicked = {
                        //code for check if email is valid
                        if (viewModel.validateEmail(uiState.email)) {
                            navController.navigate(AyoubScreen.He2b.name)
                        } else {
                            emailError = "Adresse e-mail invalide"
                            viewModel.resetEmail()
                        }
                    },

                    )
            }

            //le chemin pour les infos de l'utilisateur
            composable(route = AyoubScreen.About.name){
                displayAboutUser()
            }

            //le chemin pour le deuxième écran
            composable(route = AyoubScreen.He2b.name ){
                He2bImage()
            }
        }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    // Afficher la barre de navigation ici
    // Par exemple, vous pouvez utiliser BottomNavigation avec des éléments pour chaque destination
}