package masterj3y.github.urlshortener.domain

import java.time.Instant

data class Link(
    val shortCode: String,
    val originalUrl: String,
    val userId: Long,
    val createdAt: Instant = Instant.now(),
)
