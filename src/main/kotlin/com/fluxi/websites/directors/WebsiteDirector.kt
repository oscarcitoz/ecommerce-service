package com.fluxi.websites.directors

import com.fluxi.websites.dtos.WebsiteDirectorDTO
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

        websiteDTO.website = Website()
        return websiteDTO.website
    }

    private fun initDTO(createWebsiteRequest: CreateWebsiteRequest): WebsiteDirectorDTO {
        return WebsiteDirectorDTO().apply {
            this.request = createWebsiteRequest
        }
    }

    private fun run(dto: WebsiteDirectorDTO, phases: List<BasePhase>) {
        phases.forEach {
            it.apply(dto)
        }
    }
}