package com.fluxi.websites.controllers

import com.fluxi.websites.requests.CreateWebsiteRequest
import io.micronaut.http.annotation.*
import com.fluxi.websites.services.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fluxi.websites.models.Website

@Controller("/websites")
class WebsiteController(
    private val websiteService: WebsiteServiceInterface
) {

    @Post("/")
    fun create(@Body websiteRequest: CreateWebsiteRequest): Website {
        val objectMapper = ObjectMapper()
        val jsonString = objectMapper.writeValueAsString(websiteRequest)
        println(jsonString)

        return websiteService.create(websiteRequest)
    }
}