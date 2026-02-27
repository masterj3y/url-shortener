package masterj3y.github.urlshortener.application.ports

interface KeyGeneratorPort {
    suspend fun fetchCode(): String
}
