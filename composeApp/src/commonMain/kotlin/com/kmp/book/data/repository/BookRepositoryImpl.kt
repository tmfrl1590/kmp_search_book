package com.kmp.book.data.repository

import com.kmp.book.core.domain.DataError
import com.kmp.book.core.domain.Result
import com.kmp.book.core.domain.map
import com.kmp.book.data.mapper.BookMapper.mapperToBook
import com.kmp.book.data.network.BookRemoteSource
import com.kmp.book.domain.model.Book

class BookRepositoryImpl(
    private val bookRemoteSource: BookRemoteSource,
): BookRepository {
    override suspend fun searchBooks(query: String, page: Int): Result<Book, DataError.Remote> {
        return bookRemoteSource
            .searchBooks(query = query, page = page)
            .map { dto -> mapperToBook(dto) }
    }
}