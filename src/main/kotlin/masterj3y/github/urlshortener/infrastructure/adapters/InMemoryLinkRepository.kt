package masterj3y.github.urlshortener.infrastructure.adapters

import masterj3y.github.urlshortener.application.ports.LinkRepositoryPort
import masterj3y.github.urlshortener.domain.Link
import org.springframework.stereotype.Component

@Component
class InMemoryLinkRepository : LinkRepositoryPort {
    private val db = java.util.concurrent.ConcurrentHashMap<String, Link>()

    override suspend fun save(link: Link) = link.also { db[link.shortCode] = it }

    override suspend fun findByCode(code: String) = db[code]
}
