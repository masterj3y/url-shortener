package masterj3y.github.urlshortener.domain.exceptions

class LinkNotFoundException(
    val shortCode: String,
) : RuntimeException("Short link not found for code: $shortCode")
