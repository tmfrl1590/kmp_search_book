package com.kmp.book.presentation.main

sealed interface MainAction {
    data class OnQueryChange(val query: String): MainAction
    data object OnSearchBookList: MainAction
}