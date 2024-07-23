package com.example.mob_ayoub_project.ui.screens.login

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
import androidx.compose.ui.text.input.PasswordVisualTransformation

//login screen
@Composable
fun StartConnection(
    email: String,
    emailError: String,
    emailChange: (String) -> Unit = {},
    psw: String,
    pswChange: (String) -> Unit = {},
    onValidateClicked: () -> Unit
) {
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
                value = email,
                onValueChange = emailChange,
                modifier = Modifier.weight(3f)
            )
        }

        if (emailError.isNotEmpty()) {
            Text(text = emailError, modifier = Modifier.padding(bottom = 16.dp))
        }

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
                value = psw,
                onValueChange = pswChange,
                modifier = Modifier.weight(3f),
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Button(
            onClick = {
                onValidateClicked()

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

