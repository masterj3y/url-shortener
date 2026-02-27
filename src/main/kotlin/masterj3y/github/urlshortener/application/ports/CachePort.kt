package masterj3y.github.urlshortener.application.ports

interface CachePort {
    suspend fun get(code: String): String?

    suspend fun set(
        code: String,
        originalUrl: String,
        ttlSeconds: Long,
    )
}
