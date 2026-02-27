package masterj3y.github.urlshortener.application.ports

import masterj3y.github.urlshortener.domain.Link

interface LinkRepositoryPort {
    suspend fun save(link: Link): Link

    suspend fun findByCode(code: String): Link?
}
