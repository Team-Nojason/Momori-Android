package com.nohjason.momori.ui.onboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.momori.service.model.request.LoginRequest
import com.nohjason.momori.service.repository.auth.AuthRepository
import com.nohjason.momori.util.TAG
import com.nohjason.momori.util.toErrorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OnBoardViewModel: ViewModel() {

    val sideEffect =
        MutableStateFlow<OnBoardSideEffect>(OnBoardSideEffect.None)

    val state =
        MutableStateFlow(OnBoardState())

    fun login(idToken: String) {
        viewModelScope.launch {
            try {
                val result = AuthRepository.login(
                    LoginRequest(
                        idToken = idToken,
                        platformType = "G",
                    )
                )
                sideEffect.update {
                    OnBoardSideEffect.LoginSuccess
                }
                state.update {
                    it.copy(
                        accessToken = result.accessToken,
                        refreshToken = result.refreshToken
                    )
                }
                Log.d(TAG, "login: ${result.accessToken}")

            } catch (e: HttpException) {
                val errorResponse = e.toErrorResponse()
                val code = e.code()
                Log.d(TAG, "login: $errorResponse")
                when (code) {
                    404 -> {
                        state.update {
                            it.copy(idToken = idToken)
                        }
                        sideEffect.update {
                            OnBoardSideEffect.ToJoin
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "login: ${e.message}, ${e.javaClass}")
            }

        }
    }

    fun clearSideEffect() {
        sideEffect.update {
            OnBoardSideEffect.None
        }
    }
}