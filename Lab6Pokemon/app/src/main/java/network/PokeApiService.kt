import Model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import viewmodel.PokemonViewModel

interface PokeApiService {
    @GET("pokemon?limit=100")
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: Int): PokemonViewModel.PokemonDetails
}
