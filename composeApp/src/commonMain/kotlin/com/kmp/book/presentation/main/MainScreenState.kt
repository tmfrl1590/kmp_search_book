package com.kmp.book.presentation.main

import com.kmp.book.domain.model.Book
import com.kmp.book.domain.model.Meta

data class MainScreenState(
    val inputQuery: String = "",
    val isLoading: Boolean = false,
    val bookList: Book = Book(
        meta = Meta(
            isEnd = true,
            pageableCount = 8278,
            totalCount = 8241
        ), documents = listOf()
    ),
)
