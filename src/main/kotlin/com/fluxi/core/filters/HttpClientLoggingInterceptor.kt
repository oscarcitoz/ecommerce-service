package com.fluxi.core.filters

import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import org.reactivestreams.Publisher
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

@Filter("/**")
class HttpClientLoggingInterceptor : HttpClientFilter {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val objectMapper = ObjectMapper()

    private fun Any?.toJson(): String {
        return try {
            this?.let { objectMapper.writeValueAsString(it) } ?: "null"
        } catch (e: Exception) {
            "Error serializing body: ${e.message}"
        }
    }

    override fun doFilter(request: MutableHttpRequest<*>, chain: ClientFilterChain): Publisher<out HttpResponse<*>> {
        return Mono.from(chain.proceed(request))
            .doOnError { error ->
                logger.error("""
                    External call error:
                    URL: ${request.uri}
                    Method: ${request.method}
                    Headers: ${request.headers}
                    Request body: ${request.body.orElse(null).toJson()}
                    Error: ${error.message}
                """.trimIndent(), error)
            }
            .doOnNext { response ->
                if (response.status.code >= 400) {
                    logger.error("""
                        External response error:
                        URL: ${request.uri}
                        Method: ${request.method}
                        Headers: ${request.headers}
                        Request body: ${request.body.orElse(null).toJson()}
                        Response code: ${response.status.code}
                        Response message: ${response.body().toJson()}
                    """.trimIndent())
                }
            }
    }
} 