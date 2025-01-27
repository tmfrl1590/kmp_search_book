package com.kmp.book.di

import com.kmp.book.core.data.HttpClientFactory
import com.kmp.book.data.network.BookRemoteSource
import com.kmp.book.data.network.BookRemoteSourceImpl
import com.kmp.book.data.repository.BookRepository
import com.kmp.book.data.repository.BookRepositoryImpl
import com.kmp.book.presentation.main.viewmodel.MainScreenViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.dsl.bind

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::BookRemoteSourceImpl).bind<BookRemoteSource>()
    singleOf(::BookRepositoryImpl).bind<BookRepository>()

    viewModelOf(::MainScreenViewModel)
}