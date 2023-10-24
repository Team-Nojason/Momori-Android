package com.nohjason.momori.ui.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.momori.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel: ViewModel() {

    val sideEffect = MutableStateFlow<MainState>(MainState.None)

    fun checkLogin() {
        viewModelScope.launch {
            try {
                RetrofitClient.authAPI.check()
                sideEffect.update {
                    MainState.Success
                }
            } catch (e: HttpException) {
                when (e.code()) {
                    401 -> sideEffect.update {
                        MainState.InvalidUser
                    }
                }
            }
        }
    }
}