package com.nohjason.momori.ui.onboard

data class OnBoardState(
    val idToken: String = "",
    val accessToken: String = "",
    val refreshToken: String = ""
)

sealed class OnBoardSideEffect {
    object LoginSuccess: OnBoardSideEffect()
    object ToJoin: OnBoardSideEffect()
    object InvalidIdToken: OnBoardSideEffect()
    object None: OnBoardSideEffect()
}