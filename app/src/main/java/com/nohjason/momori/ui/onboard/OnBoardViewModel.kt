package com.nohjason.momori.ui.onboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.momori.service.model.request.LoginRequest
import com.nohjason.momori.service.repository.user.UserRepository
import com.nohjason.momori.util.TAG
import kotlinx.coroutines.launch

class OnBoardViewModel: ViewModel() {


    fun login(idToken: String) {
        viewModelScope.launch {
            val result = UserRepository.login(LoginRequest(
                idToken = idToken,
                nickname = "hello",
                profileUrl = "asdasd",
                platformType = "AOS",
                fcmKey = "test"
                ))
            Log.d(TAG, "login: ${result.accessToken}")
        }
    }
}