package com.kmp.book.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.kmp.book.app.Screen
import com.kmp.book.domain.model.Documents
import com.kmp.book.presentation.main.component.BookListArea
import com.kmp.book.presentation.main.component.InputQueryField
import com.kmp.book.presentation.main.viewmodel.MainScreenViewModel
import com.kmp.book.util.convertToAuthor
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreenRoute(
    navController: NavHostController,
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
        },
        onClick = { document ->
            navController.navigate(
                Screen.Detail(
                    thumbnail = document.thumbnail,
                    title = document.title,
                    contents = document.contents,
                    publisher = document.publisher,
                    salePrice = document.salePrice,
                    status = document.status,
                    authors = convertToAuthor(document.authors),
                    datetime = document.datetime
                )
            )
        }
    )
}

@Composable
private fun MainScreen(
    state: MainScreenState,
    onAction: (MainAction) -> Unit,
    onClick: (Documents) -> Unit,
) {
    Scaffold(

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
                .padding(horizontal = 20.dp)
        ) {
            InputQueryField(
                inputText = state.inputQuery,
                onValueChange = { inputText -> onAction(MainAction.OnQueryChange(query = inputText)) },
                onSearch = { onAction(MainAction.OnSearchBookList) },
                onClick = { onAction(MainAction.OnSearchBookList) }
            )

            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )

            BookListArea(
                state = state,
                onClick = onClick
            )
        }
    }
}