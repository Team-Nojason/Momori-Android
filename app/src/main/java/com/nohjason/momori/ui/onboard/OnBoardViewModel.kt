package com.nohjason.momori.ui.onboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.momori.service.model.request.LoginRequest
import com.nohjason.momori.service.repository.user.UserRepository
import com.nohjason.momori.util.TAG
import com.nohjason.momori.util.toErrorResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OnBoardViewModel: ViewModel() {


    fun login(idToken: String) {
        viewModelScope.launch {
            try {
                val result = UserRepository.login(LoginRequest(
                    idToken = idToken + "1",
                    nickname = "hello",
                    profileUrl = "asdasd",
                    platformType = "AOS",
                    fcmKey = "test"
                ))
                Log.d(TAG, "login: ${result.accessToken}")

            } catch (e: HttpException) {
                val errorResponse = e.toErrorResponse()
                Log.d(TAG, "login: $errorResponse")
                when (e.code()) {
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