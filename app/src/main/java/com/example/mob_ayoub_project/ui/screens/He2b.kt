package com.example.mob_ayoub_project.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mob_ayoub_project.AyoubViewModel
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
@Preview
@Composable
fun he2bPreview(){
    Mob_Ayoub_ProjectTheme{
      He2bImage()
    }
}


