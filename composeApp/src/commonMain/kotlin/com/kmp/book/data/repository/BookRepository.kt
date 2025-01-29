package com.kmp.book.data.repository

import com.kmp.book.core.domain.DataError
import com.kmp.book.core.domain.Result
import com.kmp.book.domain.model.Book

interface BookRepository {

    suspend fun searchBooks(query: String, page: Int): Result<Book, DataError.Remote>
}