package com.fluxi.websites.controllers

import com.fluxi.websites.requests.CreateWebsiteRequest
import io.micronaut.http.annotation.*
import com.fluxi.websites.services.*

@Controller("/websites")
class WebsiteController(
    private val websiteService: WebsiteServiceInterface
) {
//    @Get("/{id}")
//    fun findById(@PathVariable id: String): Website {
//        return websiteService.
//    }
//
    @Post("/")
    fun create(@Body website: CreateWebsiteRequest) {
        println(website)
        return
//        return websiteService.create(website)
    }
}