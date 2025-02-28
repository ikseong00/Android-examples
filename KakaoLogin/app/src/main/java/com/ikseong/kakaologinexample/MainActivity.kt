package com.ikseong.kakaologinexample

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ikseong.kakaologinexample.ui.theme.KakaoLoginExampleTheme
import com.ikseong.kakaologinexample.ui.theme.KakaoLoginScreen
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KakaoLoginExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    KakaoLoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        login = { kakaoLogin(this) },
                        getUser = { getUser(this) },
                        getToken = { getToken(this) }
                    )
                }
            }
        }
    }
}

fun kakaoLogin(context: Context) {
    // 로그인 조합 예제

// 카카오계정으로 로그인 공통 callback 구성
// 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e("KakaoTag", "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i("KakaoTag", "카카오계정으로 로그인 성공 ${token.accessToken}")
        }
    }

// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                Log.e("KakaoTag", "카카오톡으로 로그인 실패", error)

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            } else if (token != null) {
                Log.i("KakaoTag", "카카오톡으로 로그인 성공 ${token.accessToken}")
            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
    }
}

fun getUser(context: Context) {
    // 사용자 정보 요청 (기본)
    UserApiClient.instance.me { user, error ->
        if (error != null) {
            Log.e("KakaoTag", "사용자 정보 요청 실패", error)
        } else if (user != null) {
            Log.i(
                "KakaoTag", "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
            )
        }
    }
}

fun getToken(context: Context) {
    if (AuthApiClient.instance.hasToken()) {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                if (error is KakaoSdkError && error.isInvalidTokenError()) {
                    //로그인 필요
                    Toast.makeText(context, "KakaoSdkError, 로그인 필요", Toast.LENGTH_SHORT).show()
                } else {
                    //기타 에러
                    Log.e("KakaoTag", "토큰 정보 보기 실패", error)
                }
            }
            //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
            else if (tokenInfo != null) {
                Log.i(
                    "KakaoTag", "토큰 정보 보기 성공" +
                            "\n회원번호: ${tokenInfo.id}" +
                            "\n만료시간: ${tokenInfo.expiresIn} 초"
                )
            }
        }
    } else {
        //로그인 필요
        Toast.makeText(context, "토큰이 없음, 로그인 필요", Toast.LENGTH_SHORT).show()
    }
}