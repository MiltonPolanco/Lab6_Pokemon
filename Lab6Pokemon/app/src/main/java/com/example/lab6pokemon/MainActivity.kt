package com.example.lab6pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab6pokemon.ui.theme.Lab6PokemonTheme
import com.example.lab6pokemon.ui.theme.DetailScreen
import com.example.lab6pokemon.ui.theme.MainScreen
import viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6PokemonTheme {
                PokemonApp()
            }
        }
    }
}

@Composable
fun PokemonApp(viewModel: PokemonViewModel = viewModel()) {
    val navController = rememberNavController()


    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("main") {
                MainScreen(viewModel, navController)
            }
            composable("detail/{pokemonId}") { backStackEntry ->
                val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull()
                if (pokemonId != null) {
                    DetailScreen(viewModel, pokemonId)
                }
            }
        }
    }
}
