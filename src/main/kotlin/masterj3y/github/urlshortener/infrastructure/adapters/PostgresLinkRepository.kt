package masterj3y.github.urlshortener.infrastructure.adapters

import masterj3y.github.urlshortener.domain.Link
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PostgresLinkRepository : CoroutineCrudRepository<Link, String>
