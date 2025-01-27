package com.kmp.book.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmp.book.presentation.main.component.InputQueryField
import com.kmp.book.presentation.main.viewmodel.MainScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreenRoute(
    viewModel: MainScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MainScreen(
        state = state,
        onAction = { action ->
            when(action){
                is MainAction.OnQueryChange -> viewModel.onAction(action)
                is MainAction.OnSearchBookList -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
private fun MainScreen(
    state: MainScreenState,
    onAction: (MainAction) -> Unit,
) {
    Scaffold(

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            InputQueryField(
                inputText = state.inputQuery,
                onValueChange = { inputText ->
                    onAction(MainAction.OnQueryChange(query = inputText))
                },
                onSearch = {
                    onAction(MainAction.OnSearchBookList)
                }
            )
        }
    }
}