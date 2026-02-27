package masterj3y.github.urlshortener.infrastructure.adapters

import kotlinx.coroutines.reactor.awaitSingleOrNull
import masterj3y.github.urlshortener.application.ports.CachePort
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
@Profile("!in-memory")
class RedisCacheAdapter(
    private val redisTemplate: ReactiveStringRedisTemplate,
) : CachePort {
    override suspend fun get(code: String): String? = redisTemplate.opsForValue().get(code).awaitSingleOrNull()

    override suspend fun set(
        code: String,
        originalUrl: String,
        ttlSeconds: Long,
    ) {
        redisTemplate
            .opsForValue()
            .set(code, originalUrl, Duration.ofSeconds(ttlSeconds))
            .awaitSingleOrNull()
    }
}
