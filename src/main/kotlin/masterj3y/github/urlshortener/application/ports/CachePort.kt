package masterj3y.github.urlshortener.application.ports

interface CachePort {
    fun get(code: String): String?

    fun set(
        code: String,
        originalUrl: String,
        ttlSeconds: Long,
    )
}
