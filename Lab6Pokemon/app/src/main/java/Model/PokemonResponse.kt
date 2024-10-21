package Model

data class PokemonResponse(
    val results: List<Pokemon>
)


data class Sprites(
    val front_default: String?,
    val back_default: String?,
    val front_shiny: String?,
    val back_shiny: String?
)
