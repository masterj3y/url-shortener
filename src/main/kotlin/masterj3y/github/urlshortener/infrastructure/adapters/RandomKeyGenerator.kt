package masterj3y.github.urlshortener.infrastructure.adapters

import masterj3y.github.urlshortener.application.ports.KeyGeneratorPort
import org.springframework.stereotype.Component

@Component
class RandomKeyGeneratorAdapter : KeyGeneratorPort {
    override suspend fun fetchCode(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..7).map { chars.random() }.joinToString("")
    }
}
