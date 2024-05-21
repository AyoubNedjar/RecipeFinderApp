package com.example.mob_ayoub_project.ui.screens.recipes

import android.text.Html
import android.text.Spanned
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mob_ayoub_project.data.InfosFromOneRecipe


@Composable
fun DisplayRecipeChoosed(
    recipe: InfosFromOneRecipe,
    onButtonAddClicked: (InfosFromOneRecipe) -> Unit = {}
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {

        recipe.image?.let { imageUrl ->
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Recipe Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
        recipe.title?.let { title ->
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }


        Text(
            text = "Santé",
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            )
        )
        recipe.veryHealthy?.let { isHealthy ->
            val healthIndicator = if (isHealthy) "Sain" else "Pas très sain"
            Text(
                text = healthIndicator,
                fontSize = 16.sp,
                color = if (isHealthy) Color.Green else Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Text(
            text = "Résumé",
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            )
        )
        recipe.summary?.let { summary ->
            HtmlText(html = summary.trimIndent())
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Ingredients",
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            )
        )
        recipe.extendedIngredients.forEach { ingredient ->
            Text(
                text = "- ${ingredient.name}",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Instructions",
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp
            )
        )
        recipe.instructions?.let { instructions ->
            HtmlText(html = instructions.trimIndent())
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                onButtonAddClicked(recipe)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "add Favorites")
        }

    }


}


/**
 * to convert the html code in String
 */
@Composable
fun HtmlText(html: String) {
    val spanned = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    val annotatedString = spanned.toAnnotatedString()

    Text(
        text = annotatedString,
        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
    )
}

fun Spanned.toAnnotatedString(): AnnotatedString {
    return buildAnnotatedString {
        append(this@toAnnotatedString.toString())
        this@toAnnotatedString.getSpans(0, this@toAnnotatedString.length, Any::class.java)
            .forEach { span ->
                val start = this@toAnnotatedString.getSpanStart(span)
                val end = this@toAnnotatedString.getSpanEnd(span)
                when (span) {
                    is android.text.style.StyleSpan -> {
                        when (span.style) {
                            android.graphics.Typeface.BOLD -> {
                                addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                            }

                            android.graphics.Typeface.ITALIC -> {
                                addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                            }
                        }
                    }

                    is android.text.style.URLSpan -> {
                        addStringAnnotation("URL", span.url, start, end)
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(this@toAnnotatedString.subSequence(start, end))
                        }
                    }
                }
            }
    }
}