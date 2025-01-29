package com.kmp.book.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.book.core.domain.onSuccess
import com.kmp.book.data.repository.BookRepository
import com.kmp.book.presentation.main.MainAction
import com.kmp.book.presentation.main.MainScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val bookRepository: BookRepository,
): ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    private fun searchBookList(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true, currentPage = 1) }

            bookRepository.searchBooks(query = query, page = 1)
                .onSuccess { searchedBookList ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            bookList = searchedBookList,
                        )
                    }
                }
        }
    }

    private fun pagingGetBookList(query: String, page: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }

            bookRepository.searchBooks(query = query, page = page)
                .onSuccess { searchedBookList ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            bookList = searchedBookList.copy(
                                documents = it.bookList.documents + searchedBookList.documents
                            )
                        )
                    }
                }
        }
    }

    fun onAction(action: MainAction){
        when(action){
            is MainAction.OnQueryChange -> _state.update { it.copy(inputQuery = action.query) }
            is MainAction.OnSearchBookList -> searchBookList(query = _state.value.inputQuery)
            is MainAction.OnReset -> _state.update { it.copy(inputQuery = "") }
            is MainAction.OnAddPage -> {
                _state.update { it.copy(currentPage = it.currentPage + 1) }
                pagingGetBookList(query = _state.value.inputQuery, page = _state.value.currentPage)
            }
        }
    }
}