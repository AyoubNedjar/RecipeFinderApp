package com.example.mob_ayoub_project.ui.screens.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob_ayoub_project.ui.theme.Mob_Ayoub_ProjectTheme

@Composable
fun DisplayAboutUser(
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Ayoub Nedjar", fontWeight = FontWeight.Bold, style = TextStyle(fontSize = 24.sp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("58183", style = TextStyle(fontSize = 24.sp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("F11", style = TextStyle(fontSize = 24.sp))
    }
}

