package com.fluxi.websites.phases.creations

import com.fluxi.websites.dtos.CreateWebsiteDTO
import com.fluxi.websites.requests.CreateWebsiteRequest
import jakarta.inject.Singleton
import io.micronaut.core.annotation.Order
import java.net.URL
import java.net.HttpURLConnection
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

@Order(1)
@Singleton
class CopysWebsitePhase(
    private val baseUrl: String = "https://develop.api.fluxi.com.co/api/v1/openai-integration/generate-copys",
) : BaseCreationPhase {


    override fun apply(dto: CreateWebsiteDTO): CreateWebsiteDTO {
        val prompt = generatePrompt(dto.request)
//        val copys = generateCopys(prompt)
        val copys: Map<String, Any> = mapOf("prompt" to prompt)

        println(copys)

        dto.website.copys = copys

        // Aquí puedes utilizar el resultado de `copys` para modificar el DTO si es necesario
        return dto
    }

    private fun generatePrompt(dtoRequest: CreateWebsiteRequest): String {
        return """
            Nombre del producto: ${dtoRequest.productName}
            Descripción del producto: ${dtoRequest.productDescription}
        """.trimIndent()
    }

    private fun generateCopys(prompt: String): String {
        val url = URL(baseUrl)
        val conexion = url.openConnection() as HttpURLConnection
        conexion.requestMethod = "Post"
        conexion.doInput = true

        var writer = OutputStreamWriter(conexion.outputStream)
        writer.write(prompt)
        writer.flush()
        writer.close()

        val response = BufferedReader(InputStreamReader(conexion.inputStream)).use { it.readText() }
        conexion.disconnect()

        return response
    }
}
