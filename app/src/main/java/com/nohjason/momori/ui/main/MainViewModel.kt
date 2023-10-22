package com.nohjason.momori.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.momori.service.model.request.PostRequest
import com.nohjason.momori.service.repository.post.PostRepository
import com.nohjason.momori.util.TAG
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel : ViewModel() {
    fun addPost() {
        viewModelScope.launch {
            try {
                PostRepository.addPost(PostRequest(
                    content = "conte",
                    latitude = 1f,
                    longitude = 3f,
                    isPublic = false,
                ))
            } catch (e: HttpException) {
                Log.d(TAG, "MainViewModel ${e.message}, ${e.code()} - addPost() called")
            }
        }
    }
}