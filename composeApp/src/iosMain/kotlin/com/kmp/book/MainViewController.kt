package com.kmp.book

import androidx.compose.ui.window.ComposeUIViewController
import com.kmp.book.app.AppNavHost
import com.kmp.book.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { AppNavHost() }