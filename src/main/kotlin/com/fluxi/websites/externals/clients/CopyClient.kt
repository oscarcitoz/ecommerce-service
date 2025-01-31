package com.fluxi.websites.externals.clients

import com.fluxi.websites.externals.requests.CopyRequest
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import reactor.core.publisher.Mono

@Client("copy")
interface CopyClient {
    @Post("/api/v1/openai-integration/generate-copies-customer-analysis")
    fun generateCopies(
        @Body request: CopyRequest
    ): Mono<Map<String, Any?>>
}