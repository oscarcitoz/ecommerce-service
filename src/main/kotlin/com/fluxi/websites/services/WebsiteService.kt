package com.fluxi.websites.services

import com.fluxi.websites.directors.WebsiteDirector
import com.fluxi.websites.models.Website
import jakarta.inject.Singleton
import com.fluxi.websites.repositories.WebsiteRepository
import com.fluxi.websites.requests.CreateWebsiteRequest

@Singleton
class WebsiteService(
    private val websiteDirector: WebsiteDirector,
    private val websiteRepository: WebsiteRepository
) : WebsiteServiceInterface {

    override fun findByOwnerId(ownerId: String): List<Website> =
        websiteRepository.findByOwnerId(ownerId)

    override fun findByProductId(productId: String): List<Website> =
        websiteRepository.findByProductId(productId)

    override fun create(createWebsiteRequest: CreateWebsiteRequest): Website {
        return this.websiteDirector.make(createWebsiteRequest)
    }

//    override fun update(id: Long, website: Website): Website {
//        // Aquí te aseguras de que el id en el objeto website sea actualizado
//        val websiteToUpdate = website.copy(id = id)
//        // Guardamos la entidad actualizada
//        return websiteRepository.save(websiteToUpdate)
//    }
//
//    override fun delete(id: Long): Boolean {
//        // Verificamos si el website con el id existe y lo eliminamos
//        val website = websiteRepository.findById(id).orElse(null)
//        return if (website != null) {
//            websiteRepository.delete(website)
//            true
//        } else {
//            false
//        }
//    }
}
