package masterj3y.github.urlshortener.domain

interface LinkRepositoryPort {
    fun save(link: Link): Link

    fun findByCode(code: String): Link?
}
