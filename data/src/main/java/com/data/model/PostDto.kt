package com.data.model

import com.domain.model.PostModel

data class PostDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) {
    fun toDomain(): PostModel {
        return PostModel(id = id, title = title)
    }
}