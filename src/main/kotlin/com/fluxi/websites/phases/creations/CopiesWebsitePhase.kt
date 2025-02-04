package com.fluxi.websites.phases.creations

import com.fluxi.core.extensions.logError
import com.fluxi.websites.dtos.WebsiteDirectorDTO
import com.fluxi.websites.externals.clients.CopyClient
import com.fluxi.websites.externals.requests.CopyRequest
import com.fluxi.websites.requests.CreateWebsiteRequest
import jakarta.inject.Singleton
import io.micronaut.core.annotation.Order
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

@Order(1)
@Singleton
class CopiesWebsitePhase(
    private val copyClient: CopyClient
) : BaseCreationPhase {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun apply(dto: WebsiteDirectorDTO): Mono<WebsiteDirectorDTO> {
        val request = CopyRequest().apply {
            this.userId = dto.request.userId ?: dto.request.email ?: ""
            this.prompt = generatePrompt(dto.request)
            this.productName = dto.request.productName
        }

        return this.copyClient.generateCopies(request).map {
            dto.copies = it.data.copys

            dto
        }.retry(1).doOnError {
            it.logError(logger, "ERROR_COPIES_REQUEST")
        }
    }

    private fun generatePrompt(dtoRequest: CreateWebsiteRequest): String {
        return """
            Nombre del producto: ${dtoRequest.productName}
            Descripción del producto: ${dtoRequest.productDescription}
        """.trimIndent()
    }
}

