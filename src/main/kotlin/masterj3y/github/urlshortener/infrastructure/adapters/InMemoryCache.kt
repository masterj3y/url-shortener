package masterj3y.github.urlshortener.infrastructure.adapters

import masterj3y.github.urlshortener.application.ports.CachePort
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("in-memory")
class InMemoryCacheAdapter : CachePort {
    private val map = java.util.concurrent.ConcurrentHashMap<String, String>()

    override suspend fun get(code: String) = map[code]

    override suspend fun set(
        code: String,
        originalUrl: String,
        ttlSeconds: Long,
    ) {
        map[code] = originalUrl
    }
}
