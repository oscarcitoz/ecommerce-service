package com.fluxi.websites.controllers

import com.fluxi.websites.requests.CreateWebsiteRequest
import io.micronaut.http.annotation.*
import com.fluxi.websites.services.*
import com.fasterxml.jackson.databind.ObjectMapper

@Controller("/websites")
class WebsiteController(
    private val websiteService: WebsiteServiceInterface
) {

    @Post("/")
    fun create(@Body website: CreateWebsiteRequest) {
        val objectMapper = ObjectMapper()
        val jsonString = objectMapper.writeValueAsString(website)
        println(jsonString)
        return
//        return websiteService.create(website)
    }
}