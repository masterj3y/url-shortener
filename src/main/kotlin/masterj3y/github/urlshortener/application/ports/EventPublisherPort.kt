package masterj3y.github.urlshortener.application.ports

interface EventPublisherPort {
    fun publishLinkCreated(
        shortCode: String,
        originalUrl: String,
    )

    fun publishLinkClicked(
        shortCode: String,
        ipAddress: String,
    )
}
