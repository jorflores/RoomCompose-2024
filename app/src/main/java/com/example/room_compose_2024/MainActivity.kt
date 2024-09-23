package com.example.room_compose_2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.room_compose_2024.ui.home.HomeScreen
import com.example.room_compose_2024.ui.item.ItemEditScreen
import com.example.room_compose_2024.ui.item.ItemEntryScreen
import com.example.room_compose_2024.ui.theme.RoomCompose2024Theme
import com.example.room_compose_2024.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomCompose2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InventoryApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun InventoryApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val appViewModel: AppViewModel = viewModel()

    NavHost(navController = navController, startDestination = "HomeScreen", modifier = modifier)
    {

        composable(route = "HomeScreen") {
            HomeScreen(navController = navController, appViewModel = appViewModel)
        }

        composable(route = "ItemEntryScreen") {
            ItemEntryScreen(appViewModel = appViewModel, navController = navController)
        }

        composable(
            route = "ItemEditScreen/{itemId}",
            arguments = listOf(navArgument("itemId") {
                type = NavType.IntType
            })
        ) {

            val itemId = it.arguments?.getInt("itemId") ?: 0
            ItemEditScreen(appViewModel = appViewModel, navController = navController, itemId = itemId)

        }

    }
}

