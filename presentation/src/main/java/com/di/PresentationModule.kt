package com.di


import com.domain.repository.NoteRepository
import com.domain.repository.PostRepository
import com.domain.repository.SocketRepository
import com.presentation.navigation.AppRouter
import com.presentation.screen.PostViewModel
import com.presentation.screen.home.NoteViewModel
import com.presentation.screen.socket.SocketViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { NoteViewModel(repository = get<NoteRepository>()) }
    viewModel { PostViewModel(repository = get<PostRepository>()) }
    viewModel { SocketViewModel(socketRepository = get<SocketRepository>()) }

    single { AppRouter() }
}