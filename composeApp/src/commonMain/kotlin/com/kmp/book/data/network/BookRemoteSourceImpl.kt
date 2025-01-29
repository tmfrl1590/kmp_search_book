package com.kmp.book.data.network

import com.kmp.book.core.data.safeCall
import com.kmp.book.core.domain.DataError
import com.kmp.book.core.domain.Result
import com.kmp.book.data.dto.BookDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://dapi.kakao.com/v3/search/book"
private const val SIZE = 20

class BookRemoteSourceImpl(
    private val httpClient: HttpClient
): BookRemoteSource {
    override suspend fun searchBooks(
        query: String,
        page: Int,
    ): Result<BookDto, DataError.Remote> {
        return safeCall<BookDto> {
            httpClient.get(
                urlString = BASE_URL
            ){
                parameter("query", query)
                parameter("size", SIZE)
                parameter("page", page)
            }
        }
    }
}