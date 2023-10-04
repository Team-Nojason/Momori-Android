package com.nohjason.momori.ui.onboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class OnBoardViewModel: ViewModel() {
    val state = MutableStateFlow(OnBoardState())

    fun updateId(id: String) {
        state.value = state.value.copy(id = id)
    }
}