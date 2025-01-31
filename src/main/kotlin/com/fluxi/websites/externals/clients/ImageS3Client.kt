package com.fluxi.websites.externals.clients

import com.fluxi.websites.externals.requests.ImageS3Request
import com.fluxi.websites.externals.responses.ImageS3Response
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import reactor.core.publisher.Mono

@Client("s3")
interface ImageS3Client {
    @Post("/")
    fun upload(
        @Body request: ImageS3Request
    ): Mono<ImageS3Response>
}