package com.example.mob_ayoub_project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//C'est l'écran de connexion




@Composable
fun StartConnection(modifier: Modifier = Modifier){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Connectez-vous", modifier = Modifier.padding(bottom = 16.dp))
        // Ajoutez ici les champs de saisie pour le nom d'utilisateur et le mot de passe
        // Exemple : TextField(value = ..., onValueChange = { /* ... */ })
        Button(onClick = { /* Ajoutez ici la logique de connexion */ }) {
            Text(text = "Connexion")
        }

    }
}


@Preview
@Composable
fun StartPreview(){
    StartConnection();
}

