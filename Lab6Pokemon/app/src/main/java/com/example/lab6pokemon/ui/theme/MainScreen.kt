package com.example.lab6pokemon.ui.theme

import Model.Pokemon
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.navigation.NavHostController
import viewmodel.PokemonViewModel

@Composable
fun MainScreen(viewModel: PokemonViewModel, navController: NavHostController) {
    val pokemonList by viewModel.pokemonList.collectAsState()

    if (pokemonList.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(pokemonList) { pokemon ->
                PokemonItem(pokemon = pokemon) {
                    val pokemonId = getPokemonIdFromUrl(pokemon.url)
                    navController.navigate("detail/$pokemonId")
                }
            }
        }
    } else {
        Text(text = "Cargando Pokémon...", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {

            val imageUrl = pokemon.sprites?.front_default ?: ""

            if (imageUrl.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = "Imagen de ${pokemon.name}",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 8.dp), // Espaciado a la derecha
                    contentScale = ContentScale.Crop
                )
            }


            Text(
                text = pokemon.name,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}




private fun getPokemonIdFromUrl(url: String): Int {
    return url.trimEnd('/').takeLastWhile { it.isDigit() }.toInt()
}
