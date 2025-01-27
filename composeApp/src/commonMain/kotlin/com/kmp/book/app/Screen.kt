package com.kmp.book.app

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Main: Screen
    @Serializable
    data class Detail(
        val thumbnail: String,
        val title: String,
        val contents: String,
        val publisher: String,
        val salePrice: Int,
        val status: String,
    ): Screen
}