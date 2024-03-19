package com.example.mob_ayoub_project

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ControlledComposition
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


enum class AyoubScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),

}

@Composable
fun ControlApp(
    viewModel: AyoubViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){

    NavHost(
        navController = navController,
        startDestination = AyoubScreen.Start.name,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ){
            //the first screen 
            composable(route = AyoubScreen.Start.name){

            }


    }

}
