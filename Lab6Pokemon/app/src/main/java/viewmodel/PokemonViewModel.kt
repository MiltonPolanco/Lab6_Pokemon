package viewmodel

import Model.Pokemon
import Model.Sprites
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PokemonViewModel : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    private val _pokemonDetails = MutableStateFlow<PokemonDetails?>(null)
    val pokemonDetails: StateFlow<PokemonDetails?> = _pokemonDetails


    data class PokemonDetails(
        val name: String,
        val sprites: Sprites? = null
    )


    init {
        fetchPokemonList()
    }

    fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemonList().results
                val pokemonWithSprites = response.map { pokemon ->
                    val id = getPokemonIdFromUrl(pokemon.url)
                    val details = RetrofitInstance.api.getPokemonDetails(id)

                    
                    pokemon.copy(sprites = details.sprites)
                }
                _pokemonList.value = pokemonWithSprites
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun getPokemonIdFromUrl(url: String): Int {
        return url.trimEnd('/').takeLastWhile { it.isDigit() }.toInt()
    }


    fun fetchPokemonDetails(pokemonId: Int) {
        viewModelScope.launch {
            try {
                val details = RetrofitInstance.api.getPokemonDetails(pokemonId)
                _pokemonDetails.value = details
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
