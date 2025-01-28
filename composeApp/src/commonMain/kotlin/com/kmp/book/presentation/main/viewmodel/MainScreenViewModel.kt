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
            _state.update { it.copy(isLoading = true) }

            bookRepository.searchBooks(query = query)
                .onSuccess { searchedBookList ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            bookList = searchedBookList
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
        }
    }
}