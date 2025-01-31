package com.fluxi.websites.phases.creations

import com.fluxi.websites.dtos.WebsiteDirectorDTO
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton
import java.net.HttpURLConnection
import java.net.URL

@Order(3)
//@Singleton
class ImagesWebsitePhase(
    private val baseUrl: String = "https://btythn57iphmyh42zxgvn7olry0skzni.lambda-url.us-east-2.on.aws"
): BaseCreationPhase {

    override fun apply(dto: WebsiteDirectorDTO): WebsiteDirectorDTO {
//        val userIdOrEmail = dto.request.userId?.takeIf { it.isNotBlank() }
//            ?: dto.request.email?.takeIf { it.isNotBlank() }
//            ?: throw IllegalArgumentException("Se requiere al menos userId o userEmail para subir imágenes.")
//
//        dto.request.productImages.forEach { image ->
//            uploadImage(image, userIdOrEmail)
//        }

        dto.website.images = listOf("https://fluxi-bucket.s3.amazonaws.com/prod/assets/website/oscar123/imgs/productid/image.jpg")

        return dto
    }

    private fun uploadImage(image: String, userIdOrEmail: String) {
        try {
            val url = URL(baseUrl)
            val conexion = url.openConnection() as HttpURLConnection
            conexion.requestMethod = "POST"
            conexion.doOutput = true
            conexion.setRequestProperty("Content-Type", "application/json")

            val requestBody = """
                {
                    "file": "$image",
                    "folder": "website/gueesOrUser/$userIdOrEmail/imgs/productId",
                    "filename": "image.jpg"
                }
            """.trimIndent()


            println("--------------------"+ requestBody)

            conexion.outputStream.use { output ->
                output.flush()
            }

            val response = conexion.inputStream.bufferedReader().use { it.readText() }
            println("Respuesta del servidor: $response")

            conexion.disconnect()
        } catch (e: Exception) {
            println("Error al subir la imagen: ${e.message}")
        }
    }

}