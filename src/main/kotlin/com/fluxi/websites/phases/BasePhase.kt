package com.fluxi.websites.phases

import com.fluxi.websites.dtos.CreateWebsiteDTO

interface BasePhase {
    fun apply(dto: CreateWebsiteDTO): CreateWebsiteDTO
}