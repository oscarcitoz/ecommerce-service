package com.fluxi.websites.directors

import com.fluxi.websites.models.Website
import com.fluxi.websites.requests.CreateWebsiteRequest
import reactor.core.publisher.Mono

interface WebsiteDirectorInterface {
    fun make(createWebsiteRequest: CreateWebsiteRequest): Mono<Website>
}