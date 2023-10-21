package com.nohjason.momori.ui.onboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.momori.service.model.request.LoginRequest
import com.nohjason.momori.service.repository.user.UserRepository
import com.nohjason.momori.util.TAG
import com.nohjason.momori.util.toErrorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OnBoardViewModel: ViewModel() {

    val sideEffect = MutableStateFlow<OnBoardSideEffect>(OnBoardSideEffect.None)

    fun login(idToken: String) {
        viewModelScope.launch {
            try {
                val result = UserRepository.login(LoginRequest(
                    idToken = idToken,
                    nickname = "hello",
                    profileUrl = "asdasd",
                    platformType = "AOS",
                    fcmKey = "test"
                ))
                sideEffect.update {
                    OnBoardSideEffect.Success
                }
                Log.d(TAG, "login: ${result.accessToken}")

            } catch (e: HttpException) {
                val errorResponse = e.toErrorResponse()
                val code = e.code()
                Log.d(TAG, "login: $errorResponse")
                sideEffect.update {
                    OnBoardSideEffect.InvalidIdToken
                }
                when (code) {
                    400 -> {

                    }
                    404 -> {

                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "login: ${e.message}, ${e.javaClass}")
            }

        }
    }
}