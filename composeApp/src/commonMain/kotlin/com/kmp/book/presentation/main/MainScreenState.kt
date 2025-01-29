package com.kmp.book.presentation.main

import com.kmp.book.domain.model.Book
import com.kmp.book.domain.model.Meta

data class MainScreenState(
    val inputQuery: String = "",
    val isLoading: Boolean = false,
    val bookList: Book = Book(
        meta = Meta(
            isEnd = false,
            pageableCount = 100,
            totalCount = 100
        ), documents = listOf()
    ),

    val currentPage: Int = 1,
)
