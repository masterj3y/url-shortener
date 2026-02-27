package masterj3y.github.urlshortener.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table("links")
data class Link(
    @Id
    val shortCode: String,
    val originalUrl: String,
    val userId: Long,
    val createdAt: Instant = Instant.now(),
) : Persistable<String> {
    @Transient
    private var isNewRecord: Boolean = true

    override fun getId(): String = shortCode

    override fun isNew(): Boolean = isNewRecord

    fun markNotNew() {
        isNewRecord = false
    }
}
