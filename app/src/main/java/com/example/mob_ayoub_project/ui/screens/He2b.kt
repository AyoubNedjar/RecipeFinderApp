package com.example.mob_ayoub_project.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
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
fun He2bImage(){
    var image = painterResource(id = R.drawable.logo)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
        )

}
@Preview
@Composable
fun he2bPreview(){
    Mob_Ayoub_ProjectTheme{
      He2bImage()
    }
}


