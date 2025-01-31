package com.fluxi.websites.externals.clients

import com.fluxi.websites.externals.requests.CopyRequest
import com.fluxi.websites.externals.responses.CopyResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import reactor.core.publisher.Mono

@Client("copy")
interface CopyClient {
    @Post("/api/v1/openai-integration/gpt-generate-copys-concurrently")
    fun generateCopies(
        @Body request: CopyRequest
    ): Mono<CopyResponse>
}