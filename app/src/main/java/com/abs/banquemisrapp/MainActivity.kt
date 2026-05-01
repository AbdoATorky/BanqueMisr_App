package com.abs.banquemisrapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abs.banquemisrapp.ui.theme.BanqueMisrAppTheme
import com.abs.banquemisrapp.ui.theme.Maroon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanqueMisrAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Login(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun Login(modifier: Modifier = Modifier) {
    val usernameFieldState = rememberTextFieldState()
    val passwordFieldState = rememberTextFieldState()
    var passwordInvisible by rememberSaveable { mutableStateOf(true) }
    val isButtonEnabled by remember {
        derivedStateOf { passwordFieldState.text.isNotBlank() }
    }

    Column(
        modifier = Modifier
            .padding(top = 32.dp)
    ) {
        OutlinedTextField(
            state = usernameFieldState,
            label = { Text(text = stringResource(R.string.username)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 12.dp, end = 12.dp)
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 12.dp, end = 12.dp)

        )

        ClickableText(
            R.string.forgot_email_password,
            modifier = Modifier.padding(top = 16.dp, start = 12.dp)
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
            modifier = Modifier
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
            modifier = Modifier
                .padding(top = 16.dp, start = 12.dp)
        ) {
            Text(stringResource(R.string.need_help))
            ClickableText(R.string.contact_us, color = Maroon)
        }

    }

}
@Preview(
    showSystemUi = false, showBackground = true,
    device = "spec:width=411dp,height=891dp"
)
@Composable
private fun LoginPreview() {
    Login()
}

