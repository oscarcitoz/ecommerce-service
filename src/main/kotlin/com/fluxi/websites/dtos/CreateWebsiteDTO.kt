package com.fluxi.websites.dtos

import com.fluxi.websites.models.Website
import com.fluxi.websites.requests.CreateWebsiteRequest

class CreateWebsiteDTO {
    lateinit var request: CreateWebsiteRequest
    lateinit var website: Website
}

