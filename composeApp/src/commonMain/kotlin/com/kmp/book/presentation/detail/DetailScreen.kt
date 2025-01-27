package com.kmp.book.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kmp.book.presentation.detail.component.BookImageArea
import com.kmp.book.presentation.detail.component.BookInfoArea
import com.kmp.book.presentation.detail.component.DetailTopBar

@Composable
fun DetailScreenRoute(
    navController: NavHostController,
    thumbnail: String,
    title: String,
    contents: String,
    publisher: String,
    salePrice: Int,
    status: String,
) {
    DetailScreen(
        thumbnail = thumbnail,
        title = title,
        contents = contents,
        publisher = publisher,
        salePrice = salePrice,
        status = status,
        onNavigationClick = { navController.popBackStack() }
    )
}

@Composable
private fun DetailScreen(
    thumbnail: String,
    title: String,
    contents: String,
    publisher: String,
    salePrice: Int,
    status: String,
    onNavigationClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            DetailTopBar(
                title = title,
                onNavigationClick = onNavigationClick
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
        ) {
            BookImageArea(
                imageUrl = thumbnail,
            )
            Spacer(modifier = Modifier.height(20.dp))

            BookInfoArea(
                title = title,
                contents = contents,
                publisher = publisher,
                salePrice = salePrice,
                status = status,
            )
        }
    }
}