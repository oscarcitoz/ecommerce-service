package com.fluxi.websites.phases.creations

import com.fluxi.core.extensions.generateId
import com.fluxi.websites.dtos.WebsiteDirectorDTO
import com.fluxi.websites.externals.clients.ImageS3Client
import com.fluxi.websites.externals.requests.ImageS3Request
import com.fluxi.websites.externals.responses.ImageS3Response
import io.micronaut.core.annotation.Order
import jakarta.inject.Singleton
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Order(2)
@Singleton
class ImagesWebsitePhase(
    private val imageS3Client: ImageS3Client
) : BaseCreationPhase {
    override fun apply(dto: WebsiteDirectorDTO): Mono<WebsiteDirectorDTO> {
        return Mono.zip(
            uploadImages(dto.request.productImages, dto.request.userId == null, dto.request.userId ?: dto.request.email ?: "", "product-images"),
            uploadImageIfExists(dto.request.upSell?.image, dto.request.userId == null, dto.request.userId ?: dto.request.email ?: "", "upsell-image"),
            uploadImageIfExists(dto.request.downSell?.image, dto.request.userId == null, dto.request.userId ?: dto.request.email ?: "", "downsell-image"),
        ).map { tuple ->
            val productImages = tuple.t1
            val upsellImage = tuple.t2
            val downsellImage = tuple.t3

            dto.imagesProduct = productImages.map { it.s3Url }
            dto.upsellImage = upsellImage.s3Url
            dto.downSellImage = downsellImage.s3Url

            dto
        }
    }

    private fun uploadImages(images: List<String>, guest: Boolean, userId: String, prefix: String): Mono<List<ImageS3Response>> {
        return Flux.fromIterable(images)
            .flatMap { image -> uploadImage(image, guest, userId, prefix) }
            .collectList()
    }

    private fun uploadImageIfExists(image: String?, guest: Boolean, userId: String, prefix: String): Mono<ImageS3Response> {
        return if (image.isNullOrBlank()) {
            Mono.just(ImageS3Response())
        } else {
            uploadImage(image, guest, userId, prefix)
        }
    }

    private fun uploadImage(image: String, guest: Boolean, userId: String, prefix: String): Mono<ImageS3Response> {
        val id = generateId()

        val folder = if (guest) {
            "website/guest/$userId/$prefix"
        } else {
            "website/user/$userId/$prefix"
        }

        val request = ImageS3Request().apply {
            this.file = image
            this.filename = id
            this.folder = folder
        }

        return this.imageS3Client.upload(request).retry(1)
    }
}