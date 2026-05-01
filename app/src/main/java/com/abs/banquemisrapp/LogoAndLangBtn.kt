package com.abs.banquemisrapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abs.banquemisrapp.ui.theme.Maroon

@Composable
fun LogoAndBtn(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.bm_icon),
            contentDescription = "Logo Bank Misr"
        )

        Text(
            text = stringResource(R.string.language_toggle),
            color = Maroon,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .padding(top = 6.dp)
                .clickable {}
        )

    }
}

@Preview(showSystemUi = false, showBackground = false, backgroundColor = 0xFF000000)
@Composable
private fun LogoAndBtnPreview() {
    LogoAndBtn()
}