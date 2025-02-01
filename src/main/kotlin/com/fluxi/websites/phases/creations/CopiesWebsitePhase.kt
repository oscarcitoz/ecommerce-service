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
            this.userId = dto.request.userId?: dto.request.email ?: ""
            this.prompt = generatePrompt(dto.request)
            this.productName = dto.request.productName
        }

        return this.copyClient.generateCopies(request).retry(1).map {
            dto.copies = it.data.copys.copys

            dto
        }
    }

    private fun generatePrompt(dtoRequest: CreateWebsiteRequest): String {
        return """
            Nombre del producto: ${dtoRequest.productName}
            Descripción del producto: ${dtoRequest.productDescription}
        """.trimIndent()
    }
}

