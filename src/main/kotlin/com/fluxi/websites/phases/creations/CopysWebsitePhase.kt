package com.fluxi.websites.phases.creations

import com.fluxi.websites.dtos.WebsiteDirectorDTO
import com.fluxi.websites.externals.clients.CopyClient
import com.fluxi.websites.externals.requests.CopyRequest
import com.fluxi.websites.requests.CreateWebsiteRequest
import jakarta.inject.Singleton
import io.micronaut.core.annotation.Order
import reactor.core.publisher.Mono
import java.net.URL
import java.net.HttpURLConnection
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

@Order(1)
@Singleton
class CopysWebsitePhase(
    private val copyClient: CopyClient
) : BaseCreationPhase {

    override fun apply(dto: WebsiteDirectorDTO): Mono<WebsiteDirectorDTO> {
        val request = CopyRequest().apply {
            this.companyDescription = generatePrompt(dto.request)
        }

        return this.copyClient.generateCopies(request).map {
            dto.copies = it

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

