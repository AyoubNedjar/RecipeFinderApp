package com.example.mob_ayoub_project.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mob_ayoub_project.models.LoginViewModel
import kotlinx.coroutines.launch

//login screen
@Composable
fun StartConnection(
    onNavigate  : () -> Unit
) {

    var loginViewModel : LoginViewModel = viewModel()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isConnect by loginViewModel.fetchResult.collectAsState()
    val errorMessage by Repository.errorMessage.collectAsState()

    LaunchedEffect(isConnect){
        if(isConnect==LoginViewModel.ConnectionResult.SUCCES){
            onNavigate()
        }else if (isConnect==LoginViewModel.ConnectionResult.ERROR){
            Repository.updateErrorMessage("Connexion échouée, " +
                    " réésayez avec un nouvel email ou mot de passe")
        }else{
            Repository.updateErrorMessage("")
        }

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Connectez-vous", modifier = Modifier.padding(bottom = 16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "email",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .weight(1f)
            )
            TextField(
                value = email.value,
                onValueChange =
                {
                    email.value = it
                },
                modifier = Modifier.weight(3f)
            )
        }

        Text(text =errorMessage, modifier = Modifier.padding(bottom = 16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "password : ",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .weight(1f)
            )
            TextField(
                value = password.value,
                onValueChange = {password.value = it},
                modifier = Modifier.weight(3f),
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Button(
            onClick = {
                if(!loginViewModel.validateEmail(email.value)){//si non valide
                    Repository.updateErrorMessage("email non valide")
                }else{//si email valide
                    loginViewModel.setEmail(email.value)
                    loginViewModel.setPasswd(password.value)
                    loginViewModel.fetchUserInfos()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Se connecter")
        }
    }
}


@Preview
@Composable
fun StartPreview() {

}

