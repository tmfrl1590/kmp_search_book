package com.kmp.book.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kmp.book.presentation.detail.DetailScreenRoute
import com.kmp.book.presentation.main.MainScreenRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun AppNavHost() {
    MaterialTheme {
        MaterialTheme {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screen.Main,
            ){
                composable<Screen.Main> {
                    MainScreenRoute(
                        navController = navController
                    )
                }
                composable<Screen.Detail> { backStackEntry ->
                    val thumbnail = backStackEntry.toRoute<Screen.Detail>().thumbnail
                    val title = backStackEntry.toRoute<Screen.Detail>().title
                    val contents = backStackEntry.toRoute<Screen.Detail>().contents
                    val publisher = backStackEntry.toRoute<Screen.Detail>().publisher
                    val salePrice = backStackEntry.toRoute<Screen.Detail>().salePrice
                    val status = backStackEntry.toRoute<Screen.Detail>().status
                    DetailScreenRoute(
                        navController = navController,
                        thumbnail = thumbnail,
                        title = title,
                        contents = contents,
                        publisher = publisher,
                        salePrice = salePrice,
                        status = status,
                    )
                }
            }
        }
    }
}