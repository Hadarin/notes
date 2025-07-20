package com.data.di

import com.data.repository.SocketRepositoryImpl
import com.domain.repository.SocketRepository
import org.koin.dsl.module

val socketModule = module {
    single<SocketRepository> { SocketRepositoryImpl() }
}