package com.fluxi.websites.phases

import com.fluxi.websites.dtos.WebsiteDirectorDTO
import reactor.core.publisher.Mono

interface BasePhase {
    fun apply(dto: WebsiteDirectorDTO): Mono<WebsiteDirectorDTO>
}