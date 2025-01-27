package com.kmp.book

import androidx.compose.ui.window.ComposeUIViewController
import com.kmp.book.app.App
import com.kmp.book.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }