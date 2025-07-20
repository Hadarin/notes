package com.presentation.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.model.PostModel
import com.domain.repository.PostRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository
) : ViewModel() {

    var posts by mutableStateOf<List<PostModel>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)

    init {
        repository.getPosts()
            .onStart { isLoading = true }
            .catch { e ->
                e.printStackTrace()
                isLoading = false
            }
            .onEach { result ->
                posts = result
                isLoading = false
            }
            .launchIn(viewModelScope)

    }

    fun fetchPosts() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            val result = repository.getPostsAsResult()
            isLoading = false

            result.onSuccess {
                posts = it
            }.onFailure { e ->
                errorMessage = e.message ?: "Unknown error"
            }
        }
    }
}