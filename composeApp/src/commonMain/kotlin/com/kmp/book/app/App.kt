package com.kmp.book.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kmp.book.presentation.detail.DetailScreenRoute
import com.kmp.book.presentation.main.MainScreenRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        MaterialTheme {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screen.Main,
            ){
                composable<Screen.Main> {
                    MainScreenRoute()
                }
                composable<Screen.Detail> {
                    DetailScreenRoute()
                }
            }
        }
    }
}