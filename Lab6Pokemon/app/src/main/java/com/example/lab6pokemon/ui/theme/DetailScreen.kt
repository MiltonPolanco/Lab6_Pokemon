package com.example.lab6pokemon.ui.theme


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import viewmodel.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: PokemonViewModel, pokemonId: Int) {

    LaunchedEffect(pokemonId) {
        viewModel.fetchPokemonDetails(pokemonId)
    }


    val pokemonDetails by viewModel.pokemonDetails.collectAsState()


    if (pokemonDetails != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Detalles de ${pokemonDetails?.name}") }
                )
            }
        ) { innerPadding ->
            DetailContent(
                pokemonDetails = pokemonDetails!!,
                modifier = Modifier.padding(innerPadding)
            )
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Cargando detalles del Pokémon...")
        }
    }
}

@Composable
fun DetailContent(pokemonDetails: PokemonViewModel.PokemonDetails, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Front")
                Image(
                    painter = rememberAsyncImagePainter(pokemonDetails.sprites?.front_default),
                    contentDescription = "Imagen Frontal",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Back")
                Image(
                    painter = rememberAsyncImagePainter(pokemonDetails.sprites?.back_default),
                    contentDescription = "Imagen Trasera",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen Frontal Shiny
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Front Shiny")
                Image(
                    painter = rememberAsyncImagePainter(pokemonDetails.sprites?.front_shiny),
                    contentDescription = "Imagen Frontal Shiny",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Back Shiny")
                Image(
                    painter = rememberAsyncImagePainter(pokemonDetails.sprites?.back_shiny),
                    contentDescription = "Imagen Trasera Shiny",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
