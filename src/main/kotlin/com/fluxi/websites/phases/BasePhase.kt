package com.fluxi.websites.phases

import com.fluxi.websites.directors.WebsiteDirector
import com.fluxi.websites.dtos.WebsiteDirectorDTO

interface BasePhase {
    fun apply(dto: WebsiteDirectorDTO): WebsiteDirectorDTO
}