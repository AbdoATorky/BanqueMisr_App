package com.abs.banquemisrapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abs.banquemisrapp.data.DataSource
import com.abs.banquemisrapp.model.Services
import com.abs.banquemisrapp.ui.theme.Maroon


val services =  DataSource().getServiceItemsData()

@Composable
fun ClickableText(
    text: Int,
    url: String?,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    Text(
        text = buildAnnotatedString {
            withLink(
                LinkAnnotation.Url(
                    //Link
                    url = url.toString(),
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

@Composable
fun Login(modifier: Modifier = Modifier) {
    val usernameFieldState = rememberTextFieldState()
    val passwordFieldState = rememberTextFieldState()
    var passwordInvisible by rememberSaveable { mutableStateOf(true) }
    val isButtonEnabled by remember {
        derivedStateOf { passwordFieldState.text.isNotBlank() }
    }


    Column(
        modifier = Modifier
            .padding(top = 28.dp)
    ) {

        OutlinedTextField(
            state = usernameFieldState,
            label = { Text(text = stringResource(R.string.username)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
        )
        OutlinedSecureTextField(
            state = passwordFieldState,
            label = { Text(text = stringResource(R.string.password)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            inputTransformation = InputTransformation.maxLength(8),
            textObfuscationMode = if (passwordInvisible) TextObfuscationMode.RevealLastTyped
            else TextObfuscationMode.Visible,
            trailingIcon = {
                IconButton(onClick = { passwordInvisible = !passwordInvisible }) {
                    Icon(
                        imageVector = if (passwordInvisible) Icons.Default.VisibilityOff
                        else Icons.Default.Visibility,
                        contentDescription = if (passwordInvisible) "Show password" else "Hide password"
                    )
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 12.dp, end = 12.dp)

        )

        ClickableText(
            R.string.forgot_email_password,
            url = null,
            modifier = modifier.padding(top = 16.dp, start = 12.dp),
        )
        Button(
            onClick = { /* Handle Login */ },
            enabled = isButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Maroon,
                contentColor = Color.White,

                disabledContainerColor = Maroon.copy(alpha = 0.3f),
                disabledContentColor = Color.White.copy(alpha = 0.5f)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 28.dp, start = 12.dp, end = 12.dp)
                .height(50.dp)


        ) {
            Text(
                text = stringResource(R.string.login),
                fontSize = 18.sp,
            )
        }
        Row(
            modifier = modifier
                .padding(top = 16.dp, start = 12.dp)
        ) {
            Text(stringResource(R.string.need_help))
            ClickableText(
                R.string.contact_us,
                url = null,
                color = Maroon
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth(),
            thickness = 1.dp,
            color = Color.LightGray
        )
        ServiceList(services)
    }
}

@Composable
fun ServiceList(services: List<Services>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        services.forEach { service ->
            ServiceListItem(
                service = service,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ServiceListItem(service: Services, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(service.image),
            contentDescription = stringResource(service.name),
            modifier = Modifier.size(80.dp)
        )
        Text(
            text = stringResource(service.name),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


@Preview(
    showSystemUi = true, showBackground = true,
    device = "spec:width=411dp,height=891dp"
)
@Composable
private fun LoginPreview() {
    Login()
}




