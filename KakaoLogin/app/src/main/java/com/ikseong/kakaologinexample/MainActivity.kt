package com.ikseong.kakaologinexample

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ikseong.kakaologinexample.ui.theme.KakaoLoginExampleTheme
import com.ikseong.kakaologinexample.ui.theme.KakaoLoginScreen
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        Log.d("TAG", "keyhash : ${Utility.getKeyHash(this)}")
        setContent {
            KakaoLoginExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KakaoLoginScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}