package com.ikseong.kakaologinexample.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikseong.kakaologinexample.R

@Composable
fun KakaoLoginScreen(modifier: Modifier = Modifier) {
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
                    .padding(horizontal = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun KakaoLoginScreenPreview() {
    KakaoLoginScreen()
}