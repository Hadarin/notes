package com.data.repository

import com.data.remote.ApiService
import com.domain.model.PostModel
import com.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostRepositoryImpl(
    private val apiService: ApiService
) : PostRepository {
    override fun getPosts(): Flow<List<PostModel>> = flow {
        val posts = apiService.getPosts()
        emit(posts.map { it.toDomain() })
    }

    override suspend fun getPostsAsResult(): Result<List<PostModel>> {
        return try {
            val posts = apiService.getPosts().map { it.toDomain() }
            Result.success(posts)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}