package masterj3y.github.urlshortener.presentation.advice

import masterj3y.github.urlshortener.domain.exceptions.LinkNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(LinkNotFoundException::class)
    fun handleLinkNotFound(ex: LinkNotFoundException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.message!!)
        problemDetail.title = "Link Not Found"
        problemDetail.setProperty("timestamp", Instant.now())
        problemDetail.setProperty("invalid_code", ex.shortCode)
        return problemDetail
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ProblemDetail =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.")
            .apply { title = "Internal Server Error" }
}
