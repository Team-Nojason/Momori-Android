package com.nohjason.momori.ui.onboard

data class OnBoardState(
    val idToken: String = ""
)

sealed class OnBoardSideEffect {
    object Success: OnBoardSideEffect()
    object InvalidIdToken: OnBoardSideEffect()
    object None: OnBoardSideEffect()
}