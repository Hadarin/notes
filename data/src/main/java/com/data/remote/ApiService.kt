package com.data.remote

import com.data.model.PostDto
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}