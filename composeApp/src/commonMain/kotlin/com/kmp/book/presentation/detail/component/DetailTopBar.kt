package com.kmp.book.presentation.detail.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    title: String,
    onNavigationClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Text(
                text = title,
                fontSize = 20.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigationClick
            ){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back"
                )
            }
        }
    )
}