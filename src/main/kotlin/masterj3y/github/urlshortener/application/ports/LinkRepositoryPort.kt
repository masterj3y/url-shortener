package masterj3y.github.urlshortener.application.ports

import masterj3y.github.urlshortener.domain.Link

interface LinkRepositoryPort {
    fun save(link: Link): Link

    fun findByCode(code: String): Link?
}
