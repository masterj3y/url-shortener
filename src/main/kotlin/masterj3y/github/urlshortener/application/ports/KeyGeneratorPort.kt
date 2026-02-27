package masterj3y.github.urlshortener.application.ports

interface KeyGeneratorPort {
    fun fetchCode(): String
}
