package com.abs.banquemisrapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink


@Composable
fun ClickableText(
    text: Int,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    Text(
        text = buildAnnotatedString {
            withLink(
                LinkAnnotation.Url(
                    //Link
                    url = "",
                    styles = TextLinkStyles(
                        SpanStyle(
                            color = color,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                )
            ) {
                append(stringResource(text))
            }
        },
        modifier = modifier
    )
}

