package com.ikseong.kakaologinexample.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikseong.kakaologinexample.R

@Composable
fun KakaoLoginScreen(
    modifier: Modifier = Modifier,
    login: () -> Unit = {},
    getUser: () -> Unit = {},
    getToken: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Kakao Login Screen",
                style = MaterialTheme.typography.headlineLarge,
            )

            Image(
                painter = painterResource(R.drawable.kakao_login_large_wide),
                contentDescription = "Kakao Login Button",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable {
                        login()
                    }
            )

            Button(
                onClick = getUser,
                modifier = Modifier.padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            ) {
                Text(
                    text = "Get User",
                    color = Color.Black
                )
            }

            Button(
                onClick = getToken,
                modifier = Modifier.padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            ) {
                Text(
                    text = "Get Token",
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun KakaoLoginScreenPreview() {
    KakaoLoginScreen()
}