package com.fluxi.websites.directors

import com.fluxi.websites.models.Website
import com.fluxi.websites.requests.CreateWebsiteRequest

interface WebsiteDirectorInterface {
    fun make(createWebsiteRequest: CreateWebsiteRequest): Website
}