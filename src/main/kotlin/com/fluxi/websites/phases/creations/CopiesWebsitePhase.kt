package com.fluxi.websites.phases.creations

import com.fluxi.websites.dtos.WebsiteDirectorDTO
import com.fluxi.websites.externals.clients.CopyClient
import com.fluxi.websites.externals.requests.CopyRequest
import com.fluxi.websites.requests.CreateWebsiteRequest
import jakarta.inject.Singleton
import io.micronaut.core.annotation.Order
import reactor.core.publisher.Mono

@Order(1)
@Singleton
class CopiesWebsitePhase(
    private val copyClient: CopyClient
) : BaseCreationPhase {

    override fun apply(dto: WebsiteDirectorDTO): Mono<WebsiteDirectorDTO> {
        val request = CopyRequest().apply {
            this.companyDescription = generatePrompt(dto.request)
        }

        dto.copies = mapOf()
        return Mono.just(dto)

        /*
        return this.copyClient.generateCopies(request).map {
            dto.copies = it

            dto
        }

         */
    }

    private fun generatePrompt(dtoRequest: CreateWebsiteRequest): String {
        return """
            Nombre del producto: ${dtoRequest.productName}
            Descripción del producto: ${dtoRequest.productDescription}
        """.trimIndent()
    }
}

