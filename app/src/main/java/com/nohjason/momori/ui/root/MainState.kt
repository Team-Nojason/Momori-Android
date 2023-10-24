package com.nohjason.momori.ui.root

sealed interface MainState {
    object Success: MainState
    object InvalidUser: MainState
    object None: MainState
}