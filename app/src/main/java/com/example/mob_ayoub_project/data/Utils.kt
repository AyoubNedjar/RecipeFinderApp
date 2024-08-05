package com.example.mob_ayoub_project.data

import android.graphics.Typeface
import android.text.Html
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.URLSpan
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

object Utils {
    var apiKeyRecipe = "e9e5c00dabbd4da198c72832aa7c0619"
    const val apiKeyLogin = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRuc3Jpdm54bGVlcWR0YnloZnR2Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTY5NzE0MDI2MSwiZXhwIjoyMDEyNzE2MjYxfQ.jgJ49-c9Z8iPQnLVTnPlfRZpKwyBKht-OY8wMTceSiM"
    const val baseURLLogin = "https://dnsrivnxleeqdtbyhftv.supabase.co/"
    const val baseURLRecipe = "https://api.spoonacular.com/"

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
                        is StyleSpan -> {
                            when (span.style) {
                                Typeface.BOLD -> {
                                    addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                                }

                                Typeface.ITALIC -> {
                                    addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                                }
                            }
                        }

                        is URLSpan -> {
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

}
