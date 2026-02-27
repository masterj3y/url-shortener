package masterj3y.github.urlshortener.infrastructure.adapters

import masterj3y.github.urlshortener.application.ports.EventPublisherPort
import org.springframework.stereotype.Component

@Component
class DummyKafkaAdapter : EventPublisherPort {
    override fun publishLinkCreated(
        shortCode: String,
        originalUrl: String,
    ) {
        println("Fake Kafka -> Created: $shortCode for $originalUrl")
    }

    override fun publishLinkClicked(
        shortCode: String,
        ipAddress: String,
    ) {
        println("Fake Kafka -> Clicked: $shortCode by $ipAddress")
    }
}
