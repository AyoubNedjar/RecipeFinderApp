package com.example.mob_ayoub_project.ui.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mob_ayoub_project.R
import com.example.mob_ayoub_project.ui.theme.Mob_Ayoub_ProjectTheme


/**
 * display he2b Image
 */
@Composable
fun He2bImage() {
    val image = painterResource(id = R.drawable.logo)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp) // Taille de l'image à ajuster selon vos besoins
        )
    }
}

