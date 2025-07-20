package com.presentation.screen.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.domain.model.PostModel
import com.presentation.screen.PostViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostScreen() {

    val viewModel: PostViewModel = koinViewModel()
    val posts = viewModel.posts
    val isLoading = viewModel.isLoading

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            posts.isEmpty() -> {
                Text(
                    text = "No posts available",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyColumn {
                    items(posts) { post ->
                        PostItem(post)
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(post: PostModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(text = post.title, style = MaterialTheme.typography.titleMedium)
        Divider()
    }
}