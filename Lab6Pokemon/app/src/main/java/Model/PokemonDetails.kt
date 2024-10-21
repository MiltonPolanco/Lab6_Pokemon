package Model

data class Pokemon(
    val name: String,
    val url: String,
    val sprites: Sprites? = null
)

{
    fun copy(sprites: Sprites?): Pokemon {
        return Pokemon(name = this.name, url = this.url, sprites = sprites)
    }
}


