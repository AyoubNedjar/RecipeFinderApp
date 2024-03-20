package com.example.mob_ayoub_project.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//C'est l'écran de connexion




@Composable
fun StartConnection(
    email : String,
    emailError : String,
    emailChange : (String) -> Unit = {},
    onValidateClicked : () -> Unit,
    modifier: Modifier = Modifier){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Connectez-vous", modifier = Modifier.padding(bottom = 16.dp))
        TextField(
            value = email,
            onValueChange =emailChange,
        )
        if(emailError.isNotEmpty()){
            Text(text = emailError, modifier = Modifier.padding(bottom = 16.dp))

        }

        Button(
            //the code for check if email is valid
            onClick = { onValidateClicked() },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Se connecter")
        }

    }
}


@Preview
@Composable
fun StartPreview(){

}

