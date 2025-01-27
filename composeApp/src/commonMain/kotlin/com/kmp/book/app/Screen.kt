package com.kmp.book.app

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Main: Screen
    @Serializable
    data object Detail: Screen
}