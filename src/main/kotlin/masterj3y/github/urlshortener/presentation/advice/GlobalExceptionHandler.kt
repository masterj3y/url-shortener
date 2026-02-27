package masterj3y.github.urlshortener.presentation.advice

import masterj3y.github.urlshortener.domain.exceptions.LinkNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(LinkNotFoundException::class)
    fun handleLinkNotFound(ex: LinkNotFoundException): ProblemDetail =
        ProblemDetail
            .forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.message ?: "The requested short link was not found.",
            ).apply {
                title = "Link Not Found"
                setProperty("timestamp", Instant.now())
                setProperty("invalid_code", ex.shortCode)
            }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ProblemDetail {
        logger.error(" Unhandled exception occurred: ${ex.message}", ex)

        return ProblemDetail
            .forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred.",
            ).apply {
                title = "Internal Server Error"
                setProperty("timestamp", Instant.now())
            }
    }
}
