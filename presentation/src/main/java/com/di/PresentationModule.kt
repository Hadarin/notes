package com.di


import com.domain.repository.NoteRepository
import com.presentation.navigation.AppRouter
import com.presentation.screen.home.NoteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { NoteViewModel(repository = get<NoteRepository>()) }

    single { AppRouter() }
}