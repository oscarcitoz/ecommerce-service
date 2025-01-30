package com.fluxi.websites.directors

import com.fluxi.websites.dtos.CreateWebsiteDTO
import com.fluxi.websites.models.Website
import com.fluxi.websites.phases.BasePhase
import com.fluxi.websites.phases.creations.BaseCreationPhase
import com.fluxi.websites.requests.CreateWebsiteRequest
import jakarta.inject.Singleton
import jakarta.transaction.Transactional

@Singleton
open class WebsiteDirector(
    private val creationPhases: List<BaseCreationPhase>
): WebsiteDirectorInterface {

    @Transactional
    override fun make(createWebsiteRequest: CreateWebsiteRequest): Website {
        val websiteDTO = this.initDTO(createWebsiteRequest)
        this.run(websiteDTO, this.creationPhases)

        return websiteDTO.website
    }

    private fun initDTO(createWebsiteRequest: CreateWebsiteRequest): CreateWebsiteDTO {
        return CreateWebsiteDTO().apply {
            this.request = createWebsiteRequest
        }
    }

    private fun run(dto: CreateWebsiteDTO, phases: List<BasePhase>) {
        phases.forEach {
            it.apply(dto)
        }
    }
}