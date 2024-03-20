package com.example.mob_ayoub_project

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.mob_ayoub_project.ui.screens.He2bImage
import com.example.mob_ayoub_project.ui.screens.StartConnection


enum class AyoubScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    He2b(title = R.string.he2b)

}

@Composable
fun ControlApp(
    viewModel: AyoubViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val uiState by viewModel.uiState.collectAsState()
    var emailError by remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = AyoubScreen.Start.name,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ){

            //the first screen
            composable(route = AyoubScreen.Start.name){
                StartConnection(
                    email = uiState.email,
                    emailChange = {viewModel.setEmail(it)},
                    emailError  = emailError,
                    onValidateClicked = {
                        if (viewModel.validateEmail(uiState.email)) {
                            navController.navigate(AyoubScreen.He2b.name)
                        } else {
                            emailError = "Adresse e-mail invalide"
                        }
                    },

                )
            }

            //the secoond screen
            composable(route = AyoubScreen.He2b.name ){
                He2bImage()
            }


    }

}
