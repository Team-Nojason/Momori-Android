package com.nohjason.momori.ui.onboard

data class OnBoardState(
    val idToken: String = ""
)

sealed class OnBoardSideEffect {
    object LoginSuccess: OnBoardSideEffect()
    object ToJoin: OnBoardSideEffect()
    object None: OnBoardSideEffect()
}