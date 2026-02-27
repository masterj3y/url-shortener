package masterj3y.github.urlshortener.infrastructure.adapters

import masterj3y.github.urlshortener.application.ports.LinkRepositoryPort
import masterj3y.github.urlshortener.domain.Link
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("!in-memory")
class PostgresLinkRepositoryAdapter(
    private val crudRepository: PostgresLinkRepository,
) : LinkRepositoryPort {
    override suspend fun save(link: Link): Link = crudRepository.save(link)

    override suspend fun findByCode(code: String): Link? = crudRepository.findById(code)
}
