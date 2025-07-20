package com.domain.repository

import com.domain.model.PostModel
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<List<PostModel>>
    suspend fun getPostsAsResult(): Result<List<PostModel>>
}