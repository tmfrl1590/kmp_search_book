package com.kmp.book.data.network

import com.kmp.book.core.domain.DataError
import com.kmp.book.core.domain.Result
import com.kmp.book.data.dto.BookDto

interface BookRemoteSource {
    suspend fun searchBooks(
        query: String,
        page: Int,
    ): Result<BookDto, DataError.Remote>
}