package com.fluxi.core.extensions


fun Throwable.logError(logger: org.slf4j.Logger, origin: String) {
    if (this is io.micronaut.http.client.exceptions.HttpClientResponseException) {
        val responseBody = this.response.getBody(String::class.java).orElse("No body")
        logger.error("❌ $origin: ${this.message} - Response: $responseBody", this)
    } else {
        logger.error("❌ $origin: ${this.message}", this)
    }
}