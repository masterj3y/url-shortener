package masterj3y.github.urlshortener.application.ports

interface EventPublisherPort {
    suspend fun publishLinkCreated(
        shortCode: String,
        originalUrl: String,
    )

    suspend fun publishLinkClicked(
        shortCode: String,
        ipAddress: String,
    )
}
