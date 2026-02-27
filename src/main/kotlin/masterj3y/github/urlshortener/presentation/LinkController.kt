package masterj3y.github.urlshortener.presentation

import masterj3y.github.urlshortener.application.LinkManagerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class LinkController(
    private val linkManagerService: LinkManagerService,
) {
    data class CreateLinkRequest(
        val originalUrl: String,
        val userId: Long,
    )

    data class CreateLinkResponse(
        val shortUrl: String,
    )

    @PostMapping("/api/v1/links")
    suspend fun createLink(
        @RequestBody request: CreateLinkRequest,
    ): ResponseEntity<CreateLinkResponse> {
        val shortCode = linkManagerService.createShortLink(request.originalUrl, request.userId)
        val shortUrl = "http://localhost:8080/$shortCode"
        return ResponseEntity.ok(CreateLinkResponse(shortUrl))
    }

    @GetMapping("/{shortCode}")
    suspend fun redirect(
        @PathVariable shortCode: String,
        @RequestHeader(value = "X-Forwarded-For", defaultValue = "127.0.0.1") ipAddress: String,
    ): ResponseEntity<Void> {
        val originalUrl = linkManagerService.getOriginalUrl(shortCode, ipAddress)
        return ResponseEntity
            .status(HttpStatus.FOUND)
            .location(URI.create(originalUrl))
            .build()
    }
}
