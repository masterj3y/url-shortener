package masterj3y.github.urlshortener.application

import masterj3y.github.urlshortener.application.ports.CachePort
import masterj3y.github.urlshortener.application.ports.EventPublisherPort
import masterj3y.github.urlshortener.application.ports.KeyGeneratorPort
import masterj3y.github.urlshortener.domain.Link
import masterj3y.github.urlshortener.domain.LinkRepositoryPort
import org.springframework.stereotype.Service

@Service
class LinkManagerService(
    private val keyGenerator: KeyGeneratorPort,
    private val repository: LinkRepositoryPort,
    private val cache: CachePort,
    private val eventPublisher: EventPublisherPort,
) {
    suspend fun createShortLink(
        originalUrl: String,
        userId: Long,
    ): String {
        val shortCode = keyGenerator.fetchCode()
        val link = Link(shortCode = shortCode, originalUrl = originalUrl, userId = userId)

        repository.save(link)
        eventPublisher.publishLinkCreated(shortCode, originalUrl)

        return shortCode
    }

    suspend fun getOriginalUrl(
        shortCode: String,
        ipAddress: String,
    ): String {
        eventPublisher.publishLinkClicked(shortCode, ipAddress)

        cache.get(shortCode)?.let { return it }

        repository.findByCode(shortCode)?.let {
            cache.set(it.shortCode, it.originalUrl, 3600)
            return it.originalUrl
        } ?: throw RuntimeException("Link not found!")
    }
}
