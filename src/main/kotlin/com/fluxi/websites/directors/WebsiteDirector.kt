package com.fluxi.websites.directors

import com.fluxi.websites.dtos.WebsiteDirectorDTO
import com.fluxi.websites.models.Website
import com.fluxi.websites.phases.creations.BaseCreationPhase
import com.fluxi.websites.requests.CreateWebsiteRequest
import jakarta.inject.Singleton
import reactor.core.publisher.Mono

@Singleton
open class WebsiteDirector(
    private val creationPhases: List<BaseCreationPhase>
): WebsiteDirectorInterface {

    override fun make(createWebsiteRequest: CreateWebsiteRequest): Mono<Website> {
        val websiteDTO = this.initDTO(createWebsiteRequest)

        val monoWebsite = Mono.just(websiteDTO)
            .flatMap { dto -> applyPhasesSequentially(dto) }
            .map { it }

        return monoWebsite.map {
            it.website
        }
    }

    private fun applyPhasesSequentially(dto: WebsiteDirectorDTO): Mono<WebsiteDirectorDTO> {
        return creationPhases.fold(Mono.just(dto)) { mono, phase ->
            mono.flatMap { phase.apply(it) }
        }
    }

    private fun initDTO(createWebsiteRequest: CreateWebsiteRequest): WebsiteDirectorDTO {
        return WebsiteDirectorDTO().apply {
            this.request = createWebsiteRequest
        }
    }
}